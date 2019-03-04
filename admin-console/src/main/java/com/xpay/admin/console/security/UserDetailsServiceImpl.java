package com.xpay.admin.console.security;

import com.lx.framework.common.ReturnRestAPI;
import com.lx.framework.exception.RestAPIClientException;
import com.lx.framework.log.AppLoggerManager;
import com.lx.framework.log.Logger;
import com.lx.framework.utils.MapUtils;
import com.lx.framework.utils.StringUtils;
import com.xpay.admin.console.client.MerchantClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = AppLoggerManager.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private MerchantClient merchantClient;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ReturnRestAPI returnRestAPI = merchantClient.roleAndPermissions(username);
            if (returnRestAPI.hasError()) {
                if (returnRestAPI.getReturnCode().equalsIgnoreCase(ReturnRestAPI.NOT_EXISTS)) {
                    throw new UsernameNotFoundException(username);
                } else {
                    logger.warn("loadUserByUsername roleAndPermissions 返回错误，错误信息=" + returnRestAPI.toString());
                    throw new RestAPIClientException("roleAndPermissions 调用失败，返回信息="
                            + returnRestAPI.getReturnMessage(), returnRestAPI);
                }
            }

            Map<String, Object> objectMap = returnRestAPI.getBizContent("account");
            ArrayList<Map<String, Object>> permissionListObjectMap = returnRestAPI.getBizContent("permissions");
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (int i = 0; i < permissionListObjectMap.size(); i++) {
                for (Map.Entry<String, Object> entry : permissionListObjectMap.get(i).entrySet()) {
                    if (entry.getKey().equalsIgnoreCase("data")) {
                        authorities.add(new SimpleGrantedAuthority(StringUtils.notNull(entry.getValue())));
                    }
                }
            }

            int accountType = Integer.parseInt(objectMap.get("accountType").toString());
            if (accountType != 0) {
                logger.warn("loadUserByUsername 账号类型错误，非商家类型不能登录");
                throw new IllegalArgumentException("账号类型错误，非商家类型不能登录");
            }

            XPayUser xPayUser = new XPayUser(MapUtils.getValue(objectMap, "loginName"), MapUtils.getValue(objectMap, "loginPassword"), authorities);
            // 查询商户信息
            int mchId = Integer.parseInt(objectMap.get("objectId").toString());
            returnRestAPI = merchantClient.merchant(mchId);
            if (returnRestAPI.hasError()) {
                logger.warn("loadUserByUsername merchant 返回错误，错误信息=" + returnRestAPI.toString());
                throw new RestAPIClientException("merchant 调用失败，返回信息="
                        + returnRestAPI.getReturnMessage(), returnRestAPI);
            }

            objectMap = returnRestAPI.getBizContent("basic");
            xPayUser.setMerchantBasic(objectMap);

            return xPayUser;
        } catch (Exception ex) {
            logger.warn("loadUserByUsername 出现异常，异常信息=" + ex.getMessage(), ex);
            throw ex;
        }
    }
}
