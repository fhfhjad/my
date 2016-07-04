/**   
 * @Title: DataRongyu.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月7日 下午5:28:30
 * @version V1.0   
 */
package com.znc.mycrawler;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.domain.ProductMobile;
import com.znc.mycrawler.service.HistoryInter;
import com.znc.mycrawler.service.impl.HistoryAccessoryImp;
import com.znc.mycrawler.service.impl.HistoryMobileImpl;

/**
 * @ClassName: DataRongyu
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月7日 下午5:28:30
 */

public class DataRongyu {
	@Ignore
	@Test
	public void test() {
		HistoryInter<ProductMobile> mobile = new HistoryMobileImpl();
		
		Assert.assertEquals(true, mobile.haveRows("102273011"));
		
		mobile.parseProduct("http://m.suning.com/product/102273011.html");
		
	}
	
	@Ignore
	@Test
	public void accessory(){
		String url = "http://m.suning.com/product/126029043.html";
		System.out.println("----");
		HistoryAccessoryImp mobile = new HistoryAccessoryImp();
		mobile.parseProduct(url);
	}

}
