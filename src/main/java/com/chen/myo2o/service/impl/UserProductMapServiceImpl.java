package com.chen.myo2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.myo2o.dao.UserProductMapDao;
import com.chen.myo2o.dto.UserProductMapExecution;
import com.chen.myo2o.entity.UserProductMap;
import com.chen.myo2o.service.UserProductMapService;
import com.chen.myo2o.util.PageCalculator;

@Service
public class UserProductMapServiceImpl implements UserProductMapService {
	@Autowired
	private UserProductMapDao userProductMapDao;

	@Override
	public UserProductMapExecution listUserProductMap(
			UserProductMap userProductCondition, Integer pageIndex,
			Integer pageSize) {
		if (userProductCondition != null && pageIndex != null
				&& pageSize != null) {
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,
					pageSize);
			List<UserProductMap> userProductMapList = userProductMapDao
					.queryUserProductMapList(userProductCondition, beginIndex,
							pageSize);
			int count = userProductMapDao
					.queryUserProductMapCount(userProductCondition);
			UserProductMapExecution se = new UserProductMapExecution();
			se.setUserProductMapList(userProductMapList);
			se.setCount(count);
			return se;
		} else {
			return null;
		}

	}

}
