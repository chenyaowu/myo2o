package com.chen.myo2o.service.impl;

import com.chen.myo2o.dao.ProductSellDailyDao;
import com.chen.myo2o.service.ProductSellDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
