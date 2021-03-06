/**   
 * @Title: StandGoods.java
 * @Package com.znc.mycrawler.service.impl
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月9日 下午1:49:15
 * @version V1.0   
 */
package com.znc.mycrawler.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.znc.mycrawler.domain.ProductModel;
import com.znc.mycrawler.domain.SalesProperties;
import com.znc.mycrawler.service.GoodsModelAbstract;
import com.znc.mycrawler.util.CommonConstants;

/**
 * @ClassName: StandGoods
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月9日 下午1:49:15
 */

public class StandMobileGoods extends GoodsModelAbstract {

	public static Logger logger = Logger.getLogger(StandMobileGoods.class);

	@Override
	public String getTableName() {
		return CommonConstants.TABLE_PRODUCT_MOBILE;
	}

	

	public void saveSalerPorperties() {

		String[] attrNames = { "颜色", "机身内存" }; // 自定义销售属性

		// 1.获取表中对应的属性，选择其中的2个作为销售属性
		List<Map<String, String>> list = this
				.getColumnName(this.getTableName());
		Map<String, String> attrMap = new HashMap<String, String>();
		for (String attr : attrNames) {
			for (Map<String, String> map : list) {
				if (attr.equals(map.get("COLUMN_COMMENT"))) {
					logger.info("属性信息：" + attr + "->" + map.get("COLUMN_NAME"));
					attrMap.put(attr, map.get("COLUMN_NAME"));
				}
			}
		}

		// 2.获取所有商品型号
		List<ProductModel> listModels = this
				.getProductModel(CommonConstants.SHOUJI);
		for (ProductModel productModel : listModels) {

			for (String string : attrNames) {

				SalesProperties salesProperties = new SalesProperties();
				salesProperties.setPmid(productModel.getId());
				salesProperties.setAttributeChinese(string);
				salesProperties.setAttributeCode(attrMap.get(string));

				Map<String, String> map = new HashMap<String, String>();
				map.put("attribute", attrMap.get(string));
				map.put("tableName", this.getTableName());
				map.put("brand", productModel.getBrand());
				map.put("pmodel", productModel.getPmodel());

				List<String> attrs = this.getAttributValue(map);
				String values = Arrays.toString(attrs.toArray());
				logger.debug("brand:"+productModel.getBrand() +",pmodel:"+productModel.getPmodel() +",attribute:"+attrMap.get(string)+",values:"+values);
				salesProperties.setAttributeValues(values.substring(1,
						values.length() - 1));
				this.inserSalesProperties(salesProperties);
			}
		}
	}
}
