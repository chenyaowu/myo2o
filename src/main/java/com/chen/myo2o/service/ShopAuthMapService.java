package com.chen.myo2o.service;


import com.chen.myo2o.dto.ShopAuthMapExecution;
import com.chen.myo2o.entity.ShopAuthMap;
import com.chen.myo2o.exception.ShopAuthMapOperationException;

public interface ShopAuthMapService {
	/**
	 * 
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopAuthMapExecution listShopAuthMapByShopId(Long shopId,
												 Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @param shopAuthMap
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap)
			throws ShopAuthMapOperationException;

	/**
	 * 更新授权信息，包括职位等
	 * 

	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;

	/**
	 * 
	 * @param shopAuthMapId
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution removeShopAuthMap(Long shopAuthMapId)
			throws ShopAuthMapOperationException;

	/**
	 * 
	 * @param shopAuthId
	 * @return
	 */
	ShopAuthMap getShopAuthMapById(Long shopAuthId);

}
