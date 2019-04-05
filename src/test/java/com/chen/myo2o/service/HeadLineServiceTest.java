package com.chen.myo2o.service;

import com.chen.myo2o.BaseTest;
import com.chen.myo2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class HeadLineServiceTest extends BaseTest {
    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private CacheService cacheService;


    @Test
    public void testGetHeadLineList() throws IOException {
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
        assertEquals(2,headLineList.size());
        cacheService.removeFromCache(headLineService.HEADLINELIST);

    }
}
