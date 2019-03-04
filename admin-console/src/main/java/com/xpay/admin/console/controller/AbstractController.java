package com.xpay.admin.console.controller;

import com.lx.framework.cache.CacheKey;
import com.lx.framework.cache.CacheStrategyEnum;
import com.lx.framework.cache.Cacheable;
import com.lx.framework.common.ReturnListGeneric;
import com.lx.framework.common.ReturnRestAPI;
import com.lx.framework.log.AppLoggerManager;
import com.lx.framework.log.Logger;
import com.xpay.admin.console.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class AbstractController {
    private static final Logger logger = AppLoggerManager.getLogger(AbstractController.class);
    @Autowired
    private ProductClient productClient;

    @Cacheable(strategy = CacheStrategyEnum.Clients)
    public ReturnListGeneric getProduct(@CacheKey int merchantId) {
        ReturnRestAPI returnRestAPI = productClient.product(merchantId);
        if (returnRestAPI.hasError()) {
            logger.warn("getProduct merchantId=" + merchantId + " 出现错误，错误信息=" + returnRestAPI.getReturnMessage());
            return ReturnListGeneric.createNew(true, returnRestAPI.getReturnMessage());
        }

        List<Map<String, Object>> productList = returnRestAPI.getBizContent("product");
        ReturnListGeneric listGeneric = new ReturnListGeneric();
        listGeneric.setReturnObject(productList);
        return listGeneric;
    }
}
