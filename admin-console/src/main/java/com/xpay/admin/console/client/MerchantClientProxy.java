package com.xpay.admin.console.client;

import com.lx.framework.cache.CacheKey;
import com.lx.framework.cache.CacheStrategyEnum;
import com.lx.framework.cache.Cacheable;
import com.lx.framework.common.ReturnGeneric;
import com.lx.framework.common.ReturnRestAPI;
import com.lx.framework.log.AppLoggerManager;
import com.lx.framework.log.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yansp
 * @version 1.0
 * @date 2019-03-03
 */
@Component
public class MerchantClientProxy {
    private static final Logger logger = AppLoggerManager.getLogger(MerchantClientProxy.class);
    @Autowired
    private MerchantClient merchantClient;

    /**
     * 获取商户密钥相关信息
     *
     * @param merchantId 商户ID
     * @param bizType    业务类型
     * @return
     */
    @Cacheable(strategy = CacheStrategyEnum.Clients)
    public ReturnGeneric getMerchantSecret(@CacheKey int merchantId, @CacheKey int bizType) {
        ReturnRestAPI returnRestAPI = merchantClient.merchantSecret(merchantId, bizType);
        if (returnRestAPI.hasError()) {
            return ReturnGeneric.createNew(true, "没有获取到商户信息");
        }
        Map<String, Object> basicMap = returnRestAPI.getBizContent("basic");
        Map<String, Object> secretMap = returnRestAPI.getBizContent("secret");
        if (secretMap == null) {
            return ReturnGeneric.createNew(true, "商户没有设置业务的密钥信息");
        }

        ReturnGeneric returnGeneric = new ReturnGeneric();
        returnGeneric.putValue("basic", basicMap);
        returnGeneric.putValue("secret", secretMap);
        return returnGeneric;
    }

    /**
     * 查询商户信息
     *
     * @param merchantId
     * @return
     */
    @Cacheable(strategy = CacheStrategyEnum.Clients)
    public ReturnGeneric getMerchant(@CacheKey int merchantId) {
        ReturnRestAPI returnRestAPI = merchantClient.merchant(merchantId);
        if (returnRestAPI.hasError()) {
            return ReturnGeneric.createNew(true, "没有获取到商户信息");
        }

        Map<String, Object> basicMap = returnRestAPI.getBizContent("basic");

        ReturnGeneric returnGeneric = new ReturnGeneric();
        returnGeneric.setReturnObject(basicMap);
        return returnGeneric;
    }
}
