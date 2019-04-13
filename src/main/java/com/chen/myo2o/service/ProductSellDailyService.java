package com.chen.myo2o.service;

import com.chen.myo2o.entity.ProductSellDaily;

import java.util.Date;
import java.util.List;

public interface ProductSellDailyService {
    /**
     * 每日定时对所有店铺的商品销量进行统计
     */
    void dailyCalculate();


    List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDaily, Date beginTime,Date endTime);

}
