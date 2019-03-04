package com.xpay.admin.console.client;

import com.lx.framework.common.ReturnRestAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yansp
 * @version 1.0
 * @date 2019-03-03
 */
@FeignClient("merchant-web")
public interface MerchantClient {
    /**
     * 获取商户信息
     *
     * @param merchantId 商户ID
     * @return
     */
    @RequestMapping(value = "/m/{mch_id}", method = RequestMethod.GET)
    ReturnRestAPI merchant(@PathVariable("mch_id") int merchantId);

    /**
     * 获取商户及指定业务的密钥信息
     *
     * @param merchantId 商户ID
     * @param bizType    业务类型
     * @return
     */
    @RequestMapping(value = "/m/{mch_id}/{biz_type}", method = RequestMethod.GET)
    ReturnRestAPI merchantSecret(@PathVariable("mch_id") int merchantId, @PathVariable("biz_type") int bizType);

    /**
     * 获取账号信息、角色和权限
     *
     * @param loginName 账号登录名称
     * @return
     */
    @RequestMapping(value = "/authorize/{loginName}", method = RequestMethod.GET)
    ReturnRestAPI roleAndPermissions(@PathVariable("loginName") String loginName);
}
