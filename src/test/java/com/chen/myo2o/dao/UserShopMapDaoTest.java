package com.chen.myo2o.dao;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.entity.PersonInfo;
import com.chen.myo2o.entity.Shop;
import com.chen.myo2o.entity.UserShopMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserShopMapDaoTest extends BaseTest {
    @Autowired
    private UserShopMapDao userShopMapDao;

    @Test
    public void testInsertUserShopMap(){
        UserShopMap userShopMap = new UserShopMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1l);
        userShopMap.setUser(customer);
        Shop shop = new Shop();
        shop.setShopId(1l);
        userShopMap.setShop(shop);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(1);
        int effectNum = userShopMapDao.insertUserShopMap(userShopMap);
        assertEquals(1,effectNum);
    }

    @Test
    public void testQueryUserShopMapList(){
        UserShopMap userShopMap = new UserShopMap();
        List<UserShopMap> userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap,0,3);
        assertEquals(1,userShopMapList.size());
        int count = userShopMapDao.queryUserShopMapCount(userShopMap);
        assertEquals(1,count);
        Shop shop = new Shop();
        shop.setShopId(1l);
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1l);
        userShopMap.setUser(customer);
        userShopMap.setShop(shop);
        userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap,0,3);
        assertEquals(1,userShopMapList.size());
        count = userShopMapDao.queryUserShopMapCount(userShopMap);
        assertEquals(1,count);


    }

    @Test
    public void testUpdateUserShopMapPoint(){
        UserShopMap userShopMap =userShopMapDao.queryUserShopMap(1,1);
        userShopMap.setPoint(2);
        int effectNum = userShopMapDao.updateUserShopMapPoint(userShopMap);
        assertEquals(1,effectNum);
    }
}
