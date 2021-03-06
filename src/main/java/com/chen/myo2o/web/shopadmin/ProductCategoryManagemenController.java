package com.chen.myo2o.web.shopadmin;

import com.chen.myo2o.dto.ProductCategoryExecution;
import com.chen.myo2o.entity.ProductCategory;
import com.chen.myo2o.entity.Shop;
import com.chen.myo2o.enums.ProductCategoryStateEnum;
import com.chen.myo2o.exception.ProductCategoryOperationException;
import com.chen.myo2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import com.chen.myo2o.dto.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagemenController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<>(true, productCategoryList);
        } else {
            ProductCategoryStateEnum productCategoryStateEnum = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<>(false, productCategoryStateEnum.getState(), productCategoryStateEnum.getStateInfo());
        }
    }

    @RequestMapping(value = "/addproductcategorys",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Shop shop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory pc : productCategoryList){
            pc.setShopId(shop.getShopId());
        }
        if(productCategoryList!=null && productCategoryList.size()>0){
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
                if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproduccategory",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> removeProductCategory(Long productCategoryId,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        if(productCategoryId!=null && productCategoryId>0){
            try {
                Shop shop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,shop.getShopId());
                if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少选择一个商品类别");
        }
        return modelMap;
    }
}
