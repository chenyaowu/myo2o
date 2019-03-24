package com.chen.myo2o.dao;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.entity.ShopCategory;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
      //  List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
    //    assertEquals(1,shopCategoryList.size());
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(1l);
        testCategory.setParent(parentCategory);
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        assertEquals(1,shopCategoryList.size());
    }
}
