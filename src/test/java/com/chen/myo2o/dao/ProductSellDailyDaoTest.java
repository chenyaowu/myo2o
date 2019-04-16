package com.chen.myo2o.dao;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.entity.ProductSellDaily;
import com.chen.myo2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductSellDailyDaoTest extends BaseTest {
    @Autowired
    private ProductSellDailyDao productSellDailyDao;

    @Test
    public void testQueryProductSellDailylList(){
        ProductSellDaily productSellDaily = new ProductSellDaily();
        Shop shop = new Shop();
        shop.setShopId(1l);
        productSellDaily.setShop(shop);
        List<ProductSellDaily> productSellDailyList = productSellDailyDao.queryProductSellDailylList(productSellDaily,null,null);
        assertEquals(1,productSellDailyList.size());
    }

    @Test
    public void testInsertProductSellDaily(){
        int effectNum = productSellDailyDao.insertDefaultProductSellDaily();
        assertEquals(1,effectNum);
    }
}
