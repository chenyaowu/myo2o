package com.chen.myo2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin",method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    private String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopMamagement(){
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement")
    public String productCategoryManagement(){
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    private String productOperation(){
        return "shop/productoperation";
    }
    @RequestMapping(value = "/productmanagement")
    private String productmanagement(){
        return "shop/productmanagement";
    }

    @RequestMapping(value = "/shopauthmanagement")
    private String shopauthmanagement(){
        return "shop/shopauthmanagement";
    }

    @RequestMapping(value = "/shopauthedit")
    private String shopauthedit(){
        return "shop/shopauthedit";
    }
    @RequestMapping(value = "/productbuycheck")
    private String productBuyCheck(){
        return "shop/productbuycheck";
    }

    @RequestMapping(value = "/usershopcheck")
    private String userShopCheck(){
        return "shop/usershopcheck";
    }

    @RequestMapping(value = "/awarddelivercheck")
    private String awardDeliverCheck(){
        return "shop/awarddelivercheck";
    }
    @RequestMapping(value = "/awardmanagement")
    private String awardManagement(){
        return "shop/awardmanagement";
    }

    @RequestMapping(value = "/awardoperation")
    private String awardOperation(){
        return "shop/awardoperation";
    }
}
