package com.chen.myo2o.web.shopadmin;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.chen.myo2o.dto.EcharSeries;
import com.chen.myo2o.dto.EcharXAxis;
import com.chen.myo2o.entity.ProductSellDaily;
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
	@RequestMapping(value = "/listproductselldailyinfobyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductSellDailyInfobyShop(
			HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if(currentShop != null && currentShop.getShopId() != null){
			ProductSellDaily productSellDailyCondition = new ProductSellDaily();
			productSellDailyCondition.setShop(currentShop);
			Calendar calendar = Calendar.getInstance();
			// 获取昨天日期
			calendar.add(Calendar.DATE,-1);
			Date endTime = calendar.getTime();
			//获取7天前日期
			calendar.add(Calendar.DATE,-6);
			Date beginTime = calendar.getTime();
			// 根据传入的查询条件获取该店铺的商品销售情况
			List<ProductSellDaily> productSellDailyList = productSellDailyService.listProductSellDaily(productSellDailyCondition,beginTime,endTime);
			// 指定日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 商品名列表，保证唯一性
			HashSet<String> legendData = new HashSet<>();
			// x轴数据
			HashSet<String> xData = new HashSet<>();
			//定义series
			List<EcharSeries> series = new ArrayList<>();
			//日销量列表
			List<Integer> totalList = new ArrayList<>();
			//当前商品名，默认空
			String currentProductName = "";
			for (int i = 0; i < productSellDailyList.size(); i++) {
				ProductSellDaily productSellDaily = productSellDailyList.get(i);
				legendData.add(productSellDaily.getProduct().getProductName());
				xData.add(sdf.format(productSellDaily.getCreateTime()));
				if(!currentProductName.equals(productSellDaily.getProduct().getProductName()) && !currentProductName.isEmpty()){
					//如果currentProductName不等于获取的商品名，或者已遍历到列表的末尾，且currentProductName
					//不为空，则是遍历到下一个商品的日销量信息了，将前一轮遍历的信息放入series当中，
					//包括了商品名以及与商品名对应的统计日期以及当日销量
					EcharSeries es = new EcharSeries();
					es.setName(currentProductName);
					es.setData(totalList.subList(0,totalList.size()));
					series.add(es);
					//重置totalList
					totalList = new ArrayList<>();
					//变换下currentProductId为当前的ProductId
					currentProductName = productSellDaily.getProduct().getProductName();
					//继续添加新的值
					totalList.add(productSellDaily.getTotal());
				}else{
					//如果还是当前的productId则继续添加的新值
					totalList.add(productSellDaily.getTotal());
					currentProductName = productSellDaily.getProduct().getProductName();
				}
				if(i == productSellDailyList.size()-1){
					EcharSeries es = new EcharSeries();
					es.setName(currentProductName);
					es.setData(totalList.subList(0,totalList.size()));
					series.add(es);
				}
			}
			modelMap.put("series",series);
			modelMap.put("legendData",legendData);
			// 拼出xAxis
			List<EcharXAxis> xAxis = new ArrayList<>();
			EcharXAxis exa = new EcharXAxis();
			exa.setData(xData);
			xAxis.add(exa);
			modelMap.put("xAxis",xAxis);
			modelMap.put("success",true);

		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","empty shopId");
		}
		return modelMap;

	}
}
