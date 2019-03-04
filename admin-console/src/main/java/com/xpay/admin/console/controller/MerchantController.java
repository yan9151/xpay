package com.xpay.admin.console.controller;

import com.lx.framework.common.ReturnGeneric;
import com.lx.framework.log.AppLoggerManager;
import com.lx.framework.log.Logger;
import com.xpay.admin.console.client.MerchantClientProxy;
import com.xpay.admin.console.security.XPayUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/merchant")
public class MerchantController {
    private final Logger logger= AppLoggerManager.getLogger(MerchantController.class);

    @Autowired
    private MerchantClientProxy proxy;

    /**
     *
     * @return 商户基本信息
     */
    @RequestMapping(value = "/view.html",method = RequestMethod.GET)
    public ModelAndView view(){
        ModelAndView modelAndView=new ModelAndView("merchant/view");
        XPayUser user=(XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ReturnGeneric returnGeneric= proxy.getMerchant(user.getMerchantId());
        if (returnGeneric.hasError()){
            logger.warn("view "+ returnGeneric.getMessage()+" merchantId="+user.getMerchantId());
            modelAndView.addObject("errorMessage",returnGeneric.getMessage());
        }
        Map<String,Object> biz=returnGeneric.getReturnObject();
        modelAndView.addObject("merchant",biz);
        return modelAndView;
    }
}
