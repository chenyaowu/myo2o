package com.chen.myo2o.web.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.chen.myo2o.service.ProductSellDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.myo2o.dto.UserProductMapExecution;
import com.chen.myo2o.entity.Product;
import com.chen.myo2o.entity.Shop;
import com.chen.myo2o.entity.UserProductMap;
import com.chen.myo2o.service.UserProductMapService;
import com.chen.myo2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shop")
public class UserProductManagementController {
	@Autowired
	private UserProductMapService userProductMapService;
	@Autowired
	private ProductSellDailyService productSellDailyService;

	@RequestMapping(value = "/listuserproductmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listUserProductMapsByShop(
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute(
				"currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)
				&& (currentShop.getShopId() != null)) {
			UserProductMap userProductMapCondition = new UserProductMap();
			userProductMapCondition.setShop(currentShop);
			String productName = HttpServletRequestUtil.getString(request,
					"productName");
			if (productName != null) {
				Product product = new Product();
				product.setProductName(productName);
				userProductMapCondition.setProduct(product);
			}
			UserProductMapExecution ue = userProductMapService
					.listUserProductMap(userProductMapCondition, pageIndex,
							pageSize);
			modelMap.put("userProductMapList", ue.getUserProductMapList());
			modelMap.put("count", ue.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

}
