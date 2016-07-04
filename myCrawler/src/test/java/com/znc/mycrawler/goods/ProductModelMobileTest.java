/**   
 * @Title: ProductModelTest.java
 * @Package com.znc.mycrawler.goods
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月9日 下午2:42:29
 * @version V1.0   
 */
package com.znc.mycrawler.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.service.impl.StandMobileGoods;

/**
 * @ClassName: ProductModelTest
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月9日 下午2:42:29
 */

public class ProductModelMobileTest {
	StandMobileGoods goods = null;

	@Before
	public void init() {
		goods = new StandMobileGoods();
	}

	@Ignore
	@Test
	public void test() {
//		List<Map<String, String>> list = goods.getColumnName("product_mobile");
//		for (Map<String, String> map : list) {
//			System.out.println(map.get("COLUMN_NAME") + ":" +map.get("COLUMN_COMMENT"));
//		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("attribute", "color");
		map.put("tableName", "product_mobile");
		map.put("brand", "苹果");
		map.put("pmodel", "iPhone6");
		
		List<String> list1 = goods.getAttributValue(map);
		for (String string : list1) {
			System.out.println(string);
		}
	}
	@Ignore
	@Test
	public void saveMobileModel() { //先保存商品型号表
		goods.saveProductModelByProducts("手机",3);
		goods.saveSalerPorperties(); //再保存销售属性表

	}
	
	

}
