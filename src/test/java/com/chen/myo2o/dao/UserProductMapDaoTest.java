package com.chen.myo2o.dao;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.entity.PersonInfo;
import com.chen.myo2o.entity.Product;
import com.chen.myo2o.entity.Shop;
import com.chen.myo2o.entity.UserProductMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserProductMapDaoTest extends BaseTest {
    @Autowired
    private UserProductMapDao userProductMapDao;

    @Test
    public void testInsertUserProductMap(){
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo customer = new PersonInfo();
        customer.setUserId(1L);
        userProductMap.setUser(customer);
        userProductMap.setOperator(customer);
        Product product = new Product();
        product.setProductId(2l);
        userProductMap.setProduct(product);
        Shop shop = new Shop();
        shop.setShopId(1l);
        userProductMap.setShop(shop);
        userProductMap.setPoint(10);
        userProductMap.setCreateTime(new Date());
        int effectNum = userProductMapDao.insertUserProductMap(userProductMap);
        assertEquals(1,effectNum);
    }

    @Test
    public void testQueryUserProductMapList(){
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo customer = new PersonInfo();
        customer.setName("测试");
        userProductMap.setUser(customer);
        List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap,0,3);
        assertEquals(1,userProductMapList.size());
        int count = userProductMapDao.queryUserProductMapCount(userProductMap);
        assertEquals(1,count);

        Shop shop = new Shop();
        shop.setShopId(1l);
        userProductMap.setShop(shop);
        userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap,0,3);
        assertEquals(1,userProductMapList.size());
        count = userProductMapDao.queryUserProductMapCount(userProductMap);
        assertEquals(1,count);
    }


}
