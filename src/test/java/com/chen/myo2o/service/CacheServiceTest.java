package com.chen.myo2o.service;

import com.chen.myo2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheServiceTest extends BaseTest {
    @Autowired
    private CacheService cacheService;

    @Test
    public void testRemoveFromCache(){
        cacheService.removeFromCache("headlinelist");
    }
}
