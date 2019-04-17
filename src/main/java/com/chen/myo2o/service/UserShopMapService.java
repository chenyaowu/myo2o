package com.chen.myo2o.service;

import com.chen.myo2o.dto.UserShopMapExecution;
import com.chen.myo2o.entity.UserShopMap;

public interface UserShopMapService {

	/**
	 * 根据传入的查询信息分页查询用户分页列表
	 * @param userShopMapCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	UserShopMapExecution listUserShopMap(UserShopMap userShopMapCondition,
                                         int pageIndex, int pageSize);

}
