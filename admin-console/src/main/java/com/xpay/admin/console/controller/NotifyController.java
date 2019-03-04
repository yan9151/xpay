package com.xpay.admin.console.controller;

import com.lx.framework.common.RequestRestAPI;
import com.lx.framework.common.ReturnGeneric;
import com.lx.framework.common.ReturnListGeneric;
import com.lx.framework.common.ReturnRestAPI;
import com.lx.framework.log.AppLoggerManager;
import com.lx.framework.log.Logger;
import com.lx.framework.utils.MapUtils;
import com.lx.framework.utils.ReflectUtils;
import com.xpay.admin.console.client.OrderClient;
import com.xpay.admin.console.models.Paging;
import com.xpay.admin.console.security.XPayUser;
import com.xpay.order.common.models.NotifySearchCondition;
import com.xpay.order.common.models.TradeOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lixue
 * @date 2018-12-16
 */
@RequestMapping(value = "/notify")
@Controller
public class NotifyController extends AbstractController {
    private final static Logger logger = AppLoggerManager.getLogger(NotifyController.class);

    @Autowired
    private OrderClient orderClient;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView list() {
        XPayUser user = (XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/notify/list");
        ReturnListGeneric listGeneric = getProduct(user.getMerchantId());
        List<Map<String, Object>> productList;
        if (listGeneric.hasError()) {
            productList = new LinkedList<>();
        } else {
            productList = listGeneric.getReturnObject();
        }

        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    /**
     * 具体查询数据
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Paging doSearch(@RequestBody NotifySearchCondition searchCondition) {
        Paging<TradeOrderDTO> pageModel = new Paging<>();
        ReturnGeneric returnGeneric = searchCondition.verification();
        if (returnGeneric.hasError()) {
            pageModel.setMsg(returnGeneric.getMessage());
            return pageModel;
        }

        try {
            XPayUser user = (XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            RequestRestAPI requestRestAPI = new RequestRestAPI();
            requestRestAPI.setMerchantId(user.getMerchantId());
            requestRestAPI.setRefMerchantId(0);
            requestRestAPI.setVersion("1.0");
            requestRestAPI.setBizContent(ReflectUtils.toStringObjectMap(searchCondition));

            ReturnRestAPI returnRestAPI = orderClient.searchNotify(requestRestAPI);
            if (returnRestAPI.hasError()) {
                pageModel.setMsg(returnRestAPI.getReturnMessage());
                return pageModel;
            }

            Map<String, Object> biz = returnRestAPI.getBizContent();
            pageModel.setList(MapUtils.getValue(biz, "list"));
            int rowCount = MapUtils.getValue(biz, "rowCount");
            pageModel.setCount(Long.parseLong(String.valueOf(rowCount)));
            return pageModel;
        } catch (Exception ex) {
            logger.warn("doSearch 出现异常，返回信息=" + ex.getMessage(), ex);
            pageModel.setMsg(ex.getMessage());
            return pageModel;
        }
    }

    /**
     * 查看详情
     *
     * @param outTradeNo
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam("outTradeNo") String outTradeNo) {
        ModelAndView modelAndView = new ModelAndView();

        XPayUser user = (XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RequestRestAPI requestRestAPI = new RequestRestAPI();
        requestRestAPI.setMerchantId(user.getMerchantId());
        requestRestAPI.setRefMerchantId(0);
        requestRestAPI.setVersion("1.0");
        Map<String, Object> biz = new HashMap<>();
        biz.put("outTradeNo", outTradeNo);
        requestRestAPI.setBizContent(biz);
        ReturnRestAPI returnRestAPI = orderClient.getNotify(requestRestAPI);
        if (returnRestAPI.hasError()) {
            modelAndView.setViewName("/notify/view");
            modelAndView.addObject("errorMessage", returnRestAPI.getReturnMessage());
            return modelAndView;
        }

        biz = returnRestAPI.getBizContent();
        modelAndView.setViewName("notify/view");
        modelAndView.addObject("notify", biz);
        return modelAndView;
    }

    /**
     * 通知商户
     *
     * @param billNo
     * @return
     */
    @RequestMapping(value = "/doNotify", method = RequestMethod.GET)
    @ResponseBody
    public ReturnGeneric doNotify(@RequestParam("billNo") String billNo) {
        XPayUser user = (XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RequestRestAPI requestRestAPI = new RequestRestAPI();
        requestRestAPI.setMerchantId(user.getMerchantId());
        requestRestAPI.setRefMerchantId(0);
        requestRestAPI.setVersion("1.0");
        Map<String, Object> biz = new HashMap<>();
        biz.put("billNo", billNo);
        requestRestAPI.setBizContent(biz);
        ReturnRestAPI returnRestAPI = orderClient.doNotifyMerchant(requestRestAPI);
        if (returnRestAPI.hasError()) {
            return ReturnGeneric.createNew(true, returnRestAPI.getReturnMessage());
        }

        return ReturnGeneric.createNew(false, "通知商户完成");
    }
}
