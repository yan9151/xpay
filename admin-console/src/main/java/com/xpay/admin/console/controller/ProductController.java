package com.xpay.admin.console.controller;

import com.lx.framework.common.ReturnListGeneric;
import com.lx.framework.log.AppLoggerManager;
import com.lx.framework.log.Logger;
import com.lx.framework.utils.ReflectUtils;
import com.xpay.admin.console.models.Paging;
import com.xpay.admin.console.security.XPayUser;
import com.xpay.product.common.models.ProductDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends AbstractController {

    private Logger logger= AppLoggerManager.getLogger(ProductController.class);
    /**
     * 支付产品页面
     * @return
     */
    @RequestMapping(value = "/list.html",method = RequestMethod.GET)
    public ModelAndView view(){
        ModelAndView modelAndView=new ModelAndView("product/list");
        return modelAndView;
    }

    /**
     * 支付产品列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Paging list() {
        Paging<ProductDTO> pageModel = new Paging<>();
        XPayUser user = (XPayUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ReturnListGeneric listGeneric = getProduct(user.getMerchantId());
        if (listGeneric.hasError()) {
            pageModel.setMsg(listGeneric.getMessage());
            return pageModel;
        }
        List<Map<String,Object>>products;
        products= listGeneric.getReturnObject();
        List<ProductDTO> list=new ArrayList<>();

        for (Map<String, Object> p :
                products) {
            ProductDTO product=new ProductDTO();
            ReflectUtils.transfer(p,product);
            list.add(product);
        }
        pageModel.setList(list);
        pageModel.setCount(Long.parseLong(String.valueOf(list.size())));
        return pageModel;
    }
}
