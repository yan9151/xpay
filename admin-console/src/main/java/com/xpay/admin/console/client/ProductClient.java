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
@FeignClient("product-web")
public interface ProductClient {
    /**
     * 获取指定商户产品通道路由
     *
     * @param mchId     商户ID
     * @param productId 产品Id
     * @return
     */
    @RequestMapping(value = "/{mch_id}/{product_id}", method = RequestMethod.GET)
    ReturnRestAPI route(@PathVariable("mch_id") int mchId, @PathVariable("product_id") String productId);

    /**
     * 获取指定商户产品通道路由
     *
     * @param mchId     商户ID
     * @param refMchId  关联商户ID
     * @param productId 产品Id
     * @return
     */
    @RequestMapping(value = "/{mch_id}/{ref_mch_id}/{product_id}", method = RequestMethod.GET)
    ReturnRestAPI route(@PathVariable("mch_id") int mchId, @PathVariable("ref_mch_id") int refMchId,
                        @PathVariable("product_id") String productId);

    /**
     * 获取产品信息
     *
     * @param productId 产品ID
     * @return
     */
    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
    ReturnRestAPI product(@PathVariable("product_id") String productId);

    /**
     * 获取产品信息
     *
     * @param merchantId 商户ID
     * @return
     */
    @RequestMapping(value = "/product/merchant/{mch_id}", method = RequestMethod.GET)
    ReturnRestAPI product(@PathVariable("mch_id") int merchantId);

    /**
     * 获取产品信息
     *
     * @param merchantId 商户ID
     * @param productId  产品ID
     * @return
     */
    @RequestMapping(value = "/product/{product_id}/{mch_id}", method = RequestMethod.GET)
    ReturnRestAPI product(@PathVariable("mch_id") int merchantId, @PathVariable("product_id") String productId);
}
