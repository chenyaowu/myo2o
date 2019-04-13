package com.chen.myo2o.service;

import com.chen.myo2o.dto.UserProductMapExecution;
import com.chen.myo2o.entity.UserProductMap;

public interface UserProductMapService {
	/**
	 * 
	 * @param userProductCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	UserProductMapExecution listUserProductMap(
            UserProductMap userProductCondition, Integer pageIndex,
            Integer pageSize);

}
