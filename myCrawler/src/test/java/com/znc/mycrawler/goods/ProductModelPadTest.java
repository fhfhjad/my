/**   
 * @Title: ProductModelTest.java
 * @Package com.znc.mycrawler.goods
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月9日 下午2:42:29
 * @version V1.0   
 */
package com.znc.mycrawler.goods;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.service.impl.StandPadGoods;

/**
 * @ClassName: ProductModelTest
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月9日 下午2:42:29
 */
public class ProductModelPadTest {
	StandPadGoods goods = null;

	@Ignore
	@Before
	public void init() {
		goods = new StandPadGoods();
	}

	@Ignore
	@Test
	public void saveMobileModel() { //先保存商品型号表
		goods.saveProductModelByProducts("平板电脑",3);
		goods.saveSalerPorperties("平板电脑",3);//再保存销售属性表
	}
	
	

}
