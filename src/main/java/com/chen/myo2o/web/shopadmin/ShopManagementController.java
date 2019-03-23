package com.chen.myo2o.web.shopadmin;

import com.chen.myo2o.dto.ShopExecution;
import com.chen.myo2o.entity.PersonInfo;
import com.chen.myo2o.entity.Shop;
import com.chen.myo2o.enums.ShopStateEnum;
import com.chen.myo2o.service.ShopService;
import com.chen.myo2o.util.HttpServletRequestUtil;
import com.chen.myo2o.util.ImageUtil;
import com.chen.myo2o.util.PathUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //1.接收并转化相应的参数，包括店铺信息和图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = new Shop();
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            ShopExecution shopExecution = null;
            File shopImage = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
            try {
                inputStreamToFile(shopImg.getInputStream(), shopImage);
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
            try {
                shopExecution = shopService.addShop(shop, shopImage);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
                //该用户可以操作的店铺列表
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if (shopList == null || shopList.size() == 0) {
                    shopList = new ArrayList<Shop>();
                }
                shopList.add(shopExecution.getShop());
                request.getSession().setAttribute("shopList", shopList);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", shopExecution.getStateInfo());
                return modelMap;
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }

    }

    private static void inputStreamToFile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用inputStreamToFile异常" + e.getMessage());
        } finally {

            try {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("inputStreamToFile关闭io产生异常" + e.getMessage());
            }
        }
    }
}
