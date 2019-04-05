package com.chen.myo2o.dao;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.cache.JedisUtil;
import com.chen.myo2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;


    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }
}
