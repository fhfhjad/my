/**   
 * @Title: MyGsonTest.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月3日 下午2:22:11
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.util.JSONUtil;
import com.znc.mycrawler.util.MyHttpClient;

/**
 * @ClassName: MyGsonTest
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月3日 下午2:22:11
 */

public class MyGsonTest {
	@Ignore
	@Test
	public void test() {
		String url = "http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9017&keyword=&channel=&terminal=&cp=1&ps=20&st=0&set=5&cf=&iv=-1&ci=20006&ct=-1&yuyue=-1&n=2&channelId=WAP";
		
		String result = MyHttpClient.getMethod(url);
		
		System.out.println(result);
		
		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);
		
		System.out.println(hashMap.get("goods"));
		System.out.println(hashMap.get("goodsCount"));
		
		List<Map<String, String>> list = (List<Map<String, String>>) hashMap.get("goods");
		System.out.println(list.size());
		for (Map<String, String> obj : list) {
			System.out.println(obj.get("partnumber"));
		}
	}

}
