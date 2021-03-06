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
import com.xpay.order.common.models.TradeOrderDTO;
import com.xpay.order.common.models.TradeOrderSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends AbstractController {
    private final static Logger logger = AppLoggerManager.getLogger(OrderController.class);

    @Autowired
    private OrderClient orderClient;

    /**
     * 查询列表入口
     *
     * @return
     */
    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView list() {
        XPayUser user = (XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tradeOrder/list");
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
     * 查询记录
     *
     * @param orderCondition
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Paging doSearch(@RequestBody TradeOrderSearchCondition orderCondition) {
        Paging<TradeOrderDTO> pageModel = new Paging<>();
        ReturnGeneric returnGeneric = orderCondition.verification();
        if (returnGeneric.hasError()) {
            pageModel.setMsg(returnGeneric.getMessage());
            return pageModel;
        }

        XPayUser user = (XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RequestRestAPI requestRestAPI = new RequestRestAPI();
        requestRestAPI.setMerchantId(user.getMerchantId());
        requestRestAPI.setRefMerchantId(0);
        requestRestAPI.setVersion("1.0");
        try {
            requestRestAPI.setBizContent(ReflectUtils.toStringObjectMap(orderCondition));
            ReturnRestAPI returnRestAPI = orderClient.searchTradeorder(requestRestAPI);
            if (returnRestAPI.hasError()) {
                logger.warn("doSearch requestRestAPI=" + requestRestAPI + ",returnRestAPI=" + returnRestAPI);
                pageModel.setMsg(returnRestAPI.getReturnMessage());
                return pageModel;
            }

            Map<String, Object> biz = returnRestAPI.getBizContent();
            int rowCount = MapUtils.getValue(biz, "rowCount");
            pageModel.setList(MapUtils.getValue(biz, "list"));
            pageModel.setCount(Long.parseLong(String.valueOf(rowCount)));
            return pageModel;
        } catch (Exception e) {
            logger.warn("doSearch 出现异常，返回信息=" + e.getMessage(), e);
            pageModel.setMsg(e.getMessage());
            return pageModel;
        }
    }

    /**
     * 查看入口
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
        ReturnRestAPI returnRestAPI = orderClient.getTradeorder(requestRestAPI);
        if (returnRestAPI.hasError()) {
            modelAndView.setViewName("/tradeOrder/view");
            modelAndView.addObject("errorMessage", returnRestAPI.getReturnMessage());
            return modelAndView;
        }

        biz = returnRestAPI.getBizContent();
        modelAndView.setViewName("tradeOrder/view");
        modelAndView.addObject("order", biz);
        return modelAndView;
    }
}