package com.xpay.admin.console.client;

import com.lx.framework.common.RequestRestAPI;
import com.lx.framework.common.ReturnRestAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yansp
 * @version 1.0
 * @date 2019-03-03
 */
@FeignClient("trading-web")
public interface OrderClient {

    /**
     * 查询订单列表
     *
     * @param requestRestAPI
     * @return
     */
    @RequestMapping(value = "/trade/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnRestAPI searchTradeorder(@RequestBody RequestRestAPI requestRestAPI);

    /**
     * 查询订单明细
     *
     * @param requestRestAPI
     * @return
     */
    @RequestMapping(value = "/trade/view", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnRestAPI getTradeorder(@RequestBody RequestRestAPI requestRestAPI);

    /**
     * 通知列表查询
     *
     * @param requestRestAPI
     * @return
     */
    @RequestMapping(value = "/notify/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnRestAPI searchNotify(@RequestBody RequestRestAPI requestRestAPI);

    /**
     * 查询订单通知信息
     *
     * @param requestRestAPI
     * @return
     */
    @RequestMapping(value = "/notify/view", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnRestAPI getNotify(@RequestBody RequestRestAPI requestRestAPI);

    /**
     * 执行通知商户
     *
     * @param requestRestAPI
     * @return
     */
    @RequestMapping(value = "/notify/do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnRestAPI doNotifyMerchant(@RequestBody RequestRestAPI requestRestAPI);

    /**
     * 查询退款单据
     *
     * @param requestRestAPI
     * @return
     */
    @RequestMapping(value = "/refund/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnRestAPI searchRefuendorder(@RequestBody RequestRestAPI requestRestAPI);

    /**
     * 查询退款单据视图
     *
     * @param requestRestAPI
     * @return
     */
    @RequestMapping(value = "/refund/view", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ReturnRestAPI getRefundorder(@RequestBody RequestRestAPI requestRestAPI);
}
