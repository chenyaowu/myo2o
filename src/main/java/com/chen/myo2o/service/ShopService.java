package com.chen.myo2o.service;

import com.chen.myo2o.dto.ShopExecution;
import com.chen.myo2o.entity.Shop;
import com.chen.myo2o.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    Shop getByShopId(long shopId);

    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    //根据shopCondition返回相应列表数据
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
