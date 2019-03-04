package com.xpay.admin.console.client;

import com.lx.framework.cache.CacheStrategyEnum;
import com.lx.framework.cache.Cacheable;
import com.lx.framework.common.ReturnGeneric;
import com.lx.framework.common.ReturnRestAPI;
import com.lx.framework.log.AppLoggerManager;
import com.lx.framework.log.Logger;
import com.lx.framework.utils.MapUtils;
import com.lx.framework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yansp
 * @version 1.0
 * @date 2019-03-03
 */
@Component
public class ProductClientProxy {
    private static final Logger logger = AppLoggerManager.getLogger(ProductClientProxy.class);
    @Autowired
    private ProductClient productClient;

    /**
     * 获取指定商户的产品信息
     *
     * @param merchantId
     * @param productId
     * @return
     */
    @Cacheable(strategy = CacheStrategyEnum.Clients)
    public ReturnGeneric getProduct(int merchantId, String productId) {
        ReturnRestAPI returnRestAPI = productClient.product(merchantId, productId);
        if (returnRestAPI.hasError()) {
            logger.warn("getProduct merchantId=" + merchantId + ",productId=" + productId
                    + ",查询商户产品开通出现错误，返回报文=" + returnRestAPI.toString());
            return ReturnGeneric.createNew(true, returnRestAPI.getReturnMessage());
        }

        ReturnGeneric returnGeneric = new ReturnGeneric();
        returnGeneric.setMessage(returnRestAPI.getReturnMessage());
        returnGeneric.setReturnObject(returnRestAPI.getBizContent("product"));
        return returnGeneric;
    }

    /**
     * 获取指定商户的产品信息
     *
     * @param productId
     * @return
     */
    @Cacheable(strategy = CacheStrategyEnum.Clients)
    public ReturnGeneric getProduct(String productId) {
        ReturnRestAPI returnRestAPI = productClient.product(productId);
        if (returnRestAPI.hasError()) {
            return ReturnGeneric.createNew(true, "获取产品失败，错误信息="
                    + returnRestAPI.getReturnMessage());
        }

        Map<String, Object> bizContent = returnRestAPI.getBizContent("product");
        if (bizContent == null) {
            return ReturnGeneric.createNew(true, "没有获取到产品信息");
        }

        ReturnGeneric returnGeneric = new ReturnGeneric();
        returnGeneric.setReturnObject(bizContent);
        return returnGeneric;
    }

    /**
     * 路由通道
     *
     * @param merchantId    商户ID
     * @param refMerchantId 关联商户ID
     * @param productId     产品ID
     * @return
     */
    public ReturnGeneric getRouteChannel(int merchantId, int refMerchantId, String productId) {
        ReturnRestAPI returnRestAPI = productClient.route(merchantId, refMerchantId, productId);
        if (returnRestAPI.hasError()) {
            logger.warn("getRouteChannel merchantId=" + merchantId + ",refMercantId=" + refMerchantId
                    + ",productId=" + productId + ",获取路由通道出现错误，返回报文=" + returnRestAPI);
            return ReturnGeneric.createNew(true, returnRestAPI.getReturnMessage());
        }
        Map<String, Object> stringObjectMap = returnRestAPI.getBizContent("route");
        String channelId = MapUtils.getValue(stringObjectMap, "channelId", "");
        String channelName = MapUtils.getValue(stringObjectMap, "channelName", "");
        if (StringUtils.isEmpty(channelId) || StringUtils.isEmpty(channelName)) {
            logger.warn("getRouteChannel merchantId=" + merchantId + ",refMerchantId=" + refMerchantId
                    + ",productId=" + productId + ",没有返回必须的参数值，返回报文=" + returnRestAPI);
            return ReturnGeneric.createNew(true, "接口调用返回成功，但没有返回必须的参数值");
        }

        ReturnGeneric returnGeneric = ReturnGeneric.createNew(false, "路由获取成功");
        returnGeneric.putValue("channelId", channelId);
        returnGeneric.putValue("channelName", channelName);
        return returnGeneric;
    }
}
