package com.chen.myo2o.service;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryServiceTest extends BaseTest {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testGetShopCategoryList(){
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(1l);

        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setParent(parent);
        List<ShopCategory> shopCategoryList =shopCategoryService.getShopCategoryList(shopCategory);
        assertEquals(1,shopCategoryList.size());
    }
}
