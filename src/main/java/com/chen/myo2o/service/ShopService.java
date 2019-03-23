package com.chen.myo2o.service;

import com.chen.myo2o.dto.ShopExecution;
import com.chen.myo2o.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
