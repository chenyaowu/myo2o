package com.chen.myo2o.quartz;

import com.chen.myo2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class QuartzTest extends BaseTest{
    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Test
    public void test(){
        schedulerFactory.start();
    }

}
