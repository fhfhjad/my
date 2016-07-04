/**   
 * @Title: Mobile.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月3日 下午2:45:52
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.znc.mycrawler.domain.ProductMobile;
import com.znc.mycrawler.service.HistoryInter;
import com.znc.mycrawler.service.impl.HistoryMobileImpl;
import com.znc.mycrawler.util.JSONUtil;
import com.znc.mycrawler.util.MyHttpClient;

/**
 * @ClassName: Mobile
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月3日 下午2:45:52
 */

public class Mobile {

	public static Logger logger = Logger.getLogger(Mobile.class);

	public static void recursive(int index, String url) {
		
		logger.info("get list url = " + url);
		String result = MyHttpClient.getMethod(url);
		logger.info(" return listUrlJsonString = " +result);
		if(result.equals("")){ //如果返回的结果为空，将不再执行
			return;
		}
		
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);

		List<Map<String, String>> list = (List<Map<String, String>>) hashMap
				.get("goods"); //当前页面的商品信息

		HistoryInter<ProductMobile> mobile = new HistoryMobileImpl();

		for (Map<String, String> obj : list) {
			String partnumber = obj.get("partnumber");
			StringBuffer suningurl = new StringBuffer(
					"http://m.suning.com/product/");
			suningurl.append(partnumber).append(".html");

			logger.info("detail url =" + suningurl.toString());

			mobile.parseProduct(suningurl.toString()); // 解析入库
		}
		if (index == 0) {
			String goodsCountStr = (String) hashMap.get("goodsCount");
			int goodsCount = Integer.parseInt(goodsCountStr); // 总共多少条产品信息
			int pageNum = 20; // 每页显示20条记录
			int totalPage = (goodsCount + pageNum - 1) / pageNum;// 总页数
			logger.info("totalPage =" + totalPage);
			for (int i = 1; i < totalPage; i++) {
				StringBuffer listUrl = new StringBuffer(
						"http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9017&keyword=&channel=&terminal=&cp=");
				listUrl.append(i)
						.append("&ps=20&st=0&set=5&cf=&iv=-1&ci=20006&ct=-1&yuyue=-1&n=2&channelId=WAP");
				logger.info("goods list url =" + listUrl.toString());
				recursive(i, listUrl.toString());
			}
		}
	}

	public static void main(String[] args) {
		
		if(args.length == 1){
			int cp = Integer.parseInt(args[0]); //断点续传
			if(cp == 0) return; //不能从0开始续传
			
			StringBuffer listUrl = new StringBuffer(
					"http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9017&keyword=&channel=&terminal=&cp=");
			listUrl.append(cp)
					.append("&ps=20&st=0&set=5&cf=&iv=-1&ci=20006&ct=-1&yuyue=-1&n=2&channelId=WAP");
			logger.info("reGet goods list url =" + listUrl.toString());
			
			String result = MyHttpClient.getMethod(listUrl.toString());
			
			logger.info(" return listUrlJsonString = " +result);
			if(result.equals("")){ //如果返回的结果为空，将不再执行
				return;
			}
			
			Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
					.JSONToObject(result, Map.class);
			
			String goodsCountStr = (String) hashMap.get("goodsCount");
			int goodsCount = Integer.parseInt(goodsCountStr); // 总共多少条产品信息
			
			logger.info("goods Count =" + goodsCountStr);
			
			int pageNum = 20; // 每页显示20条记录
			int totalPage = (goodsCount + pageNum - 1) / pageNum;// 总页数
			logger.info("totalPage =" + totalPage);
			for (int i = cp; i < totalPage; i++) {
				StringBuffer xulistUrl = new StringBuffer(
						"http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9017&keyword=&channel=&terminal=&cp=");
				xulistUrl.append(i)
						.append("&ps=20&st=0&set=5&cf=&iv=-1&ci=20006&ct=-1&yuyue=-1&n=2&channelId=WAP");
				logger.info("goods xulist url =" + xulistUrl.toString());
				recursive(i, xulistUrl.toString());
			}
			
			return;
		}
		
		// 所有手机url
		String url = "http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9017&keyword=&channel=&terminal=&cp=0&ps=20&st=0&set=5&cf=&iv=-1&ci=20006&ct=-1&yuyue=-1&n=2&channelId=WAP";
		recursive(0, url);
	}

}
