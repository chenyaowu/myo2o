package com.chen.myo2o.dao;

import com.chen.myo2o.entity.ProductSellDaily;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductSellDailyDao {
    List<ProductSellDaily> queryProductSellDailylList(@Param("productSellDailyCondition") ProductSellDaily productSellDaily, @Param("beginTime")Date beginTime,@Param("endTime") Date endTime);

    int insertProductSellDaily();

    int insertDefaultProductSellDaily();


}
