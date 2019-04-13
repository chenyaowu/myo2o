package com.chen.myo2o.service.impl;

import com.chen.myo2o.dao.ProductSellDailyDao;
import com.chen.myo2o.entity.ProductSellDaily;
import com.chen.myo2o.service.ProductSellDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService{
    private static final Logger logger = LoggerFactory.getLogger(ProductSellDailyServiceImpl.class);
    @Autowired
    private ProductSellDailyDao productSellDailyDao;


    @Override
    public void dailyCalculate() {
        logger.info("Quartz Running...");
        productSellDailyDao.insertProductSellDaily();
    }

    @Override
    public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDaily, Date beginTime, Date endTime) {
        return productSellDailyDao.queryProductSellDailylList(productSellDaily,beginTime,endTime);
    }
}
