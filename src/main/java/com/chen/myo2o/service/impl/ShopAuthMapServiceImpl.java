package com.chen.myo2o.service.impl;

import java.util.Date;
import java.util.List;

import com.chen.myo2o.exception.ShopAuthMapOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chen.myo2o.dao.ShopAuthMapDao;
import com.chen.myo2o.dto.ShopAuthMapExecution;
import com.chen.myo2o.entity.ShopAuthMap;
import com.chen.myo2o.enums.ShopAuthMapStateEnum;
import com.chen.myo2o.service.ShopAuthMapService;
import com.chen.myo2o.util.PageCalculator;

@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {
    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    @Override
    public ShopAuthMapExecution listShopAuthMapByShopId(Long shopId,
                                                        Integer pageIndex, Integer pageSize) {
        if (shopId != null && pageIndex != null && pageSize != null) {
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex,
                    pageSize);
            List<ShopAuthMap> shopAuthMapList = shopAuthMapDao
                    .queryShopAuthMapListByShopId(shopId, beginIndex, pageSize);
            int count = shopAuthMapDao.queryShopAuthCountByShopId(shopId);
            ShopAuthMapExecution se = new ShopAuthMapExecution();
            se.setShopAuthMapList(shopAuthMapList);
            se.setCount(count);
            return se;
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap)
            throws RuntimeException {
        if (shopAuthMap != null && shopAuthMap.getShop() != null &&
                shopAuthMap.getShop().getShopId() != null &&
                shopAuthMap.getEmployee() != null
                && shopAuthMap.getEmployee().getUserId() != null) {
            shopAuthMap.setCreateTime(new Date());
            shopAuthMap.setLastEditTime(new Date());
            shopAuthMap.setEnableStatus(1);
            shopAuthMap.setTitleFlag(0);
            try {
                //添加授权信息
                int effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
                if (effectedNum <= 0) {
                    throw new ShopAuthMapOperationException("添加授权失败");
                }
                return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,
                        shopAuthMap);
            } catch (Exception e) {
                throw new ShopAuthMapOperationException("添加授权失败:" + e.toString());
            }
        } else {
            return new ShopAuthMapExecution(
                    ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
        }
    }

    @Override
    @Transactional
    public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap)
            throws ShopAuthMapOperationException {
        if (shopAuthMap == null || shopAuthMap.getShopAuthId() == null) {
            return new ShopAuthMapExecution(
                    ShopAuthMapStateEnum.NULL_SHOPAUTH_ID);
        } else {
            try {
                int effectedNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
                if (effectedNum <= 0) {
                    return new ShopAuthMapExecution(
                            ShopAuthMapStateEnum.INNER_ERROR);
                } else {// 创建成功
                    return new ShopAuthMapExecution(
                            ShopAuthMapStateEnum.SUCCESS, shopAuthMap);
                }
            } catch (Exception e) {
                throw new ShopAuthMapOperationException("updateShopByOwner error: "
                        + e.getMessage());
            }
        }
    }

    @Override
    public ShopAuthMapExecution removeShopAuthMap(Long shopAuthMapId)
            throws ShopAuthMapOperationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
        return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
    }

}
