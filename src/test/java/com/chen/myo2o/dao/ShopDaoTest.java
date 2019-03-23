package com.chen.myo2o.dao;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.entity.Area;
import com.chen.myo2o.entity.PersonInfo;
import com.chen.myo2o.entity.Shop;
import com.chen.myo2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopDaoTest extends BaseTest{
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1l);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1l);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);

    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1l);
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1l);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1l);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }
}
