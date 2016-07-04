/**   
 * @Title: Pad.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月21日 上午9:19:59
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.mycrawler.dao.ProductClassfiyMapper;
import com.znc.mycrawler.domain.ProductClassfiy;
import com.znc.mycrawler.domain.ProductClassfiyExample;
import com.znc.mycrawler.service.impl.HistoryAccessoryImp;
import com.znc.mycrawler.util.JSONUtil;
import com.znc.mycrawler.util.MyHttpClient;
import com.znc.mycrawler.util.MySessionFactory;

/**
 * @ClassName: Pad
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月21日 上午9:19:59
 */

public class Accessory {
	public static Logger logger = Logger.getLogger(Accessory.class);

	public static int pageNum = 20; // 默认每页显示20条记录

	public static HistoryAccessoryImp mobile = new HistoryAccessoryImp(); // 解析pad

	// http://m.suning.com/list/20017-0.html
	// http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9173&keyword=&channel=&terminal=&cp=0&ps=20&st=0&set=5&cf=&iv=-1&ci=20017&ct=-1&yuyue=-1&n=2&channelId=WAP
	// ci:20017 cf:空
	// http://m.suning.com/list/20016-10103.html
	// http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9173&keyword=&channel=&terminal=&cp=0&ps=20&st=0&set=5&cf=10103&iv=-1&ci=20016&ct=-1&yuyue=-1&n=2&channelId=WAP
	// ci:20016 cf:10103
	public static String getlistUrl(int cp, String ci, String cf) {
		StringBuffer listUrl = new StringBuffer(
				"http://search.suning.com/emall/mobile/mobileSearch.jsonp?cityId=9173&keyword=&channel=&terminal=&cp=");
		listUrl.append(cp).append("&ps=20&st=0&set=5&cf=");
		if (!"0".equals(cf))
			listUrl.append(cf);
		listUrl.append("&iv=-1&ci=").append(ci)
				.append("&ct=-1&yuyue=-1&n=2&channelId=WAP");
		return listUrl.toString();
	}

	// 获取配件的url
	public static Map<String, String> getClassUrl() {//
		Map<String, String> map = new HashMap<String, String>();
		Document doc;
		try {
			doc = Jsoup.connect("http://m.suning.com/list/list.html").get();
			Elements fisrtLevels = doc.select("ul.sub-searchers-items li"); // 所有的菜单
			for (int i = 0; i < fisrtLevels.size(); i++) {
				Element e = fisrtLevels.get(i);// 遍历所有li
				Elements eh = e.select("dd h3");// 一级节点
				for (Element element : eh) {
					String level1Name = element.text().trim(); // 一级节点名称
					if ("手机数码".equals(level1Name)) {
						Element e2 = fisrtLevels.get(i + 1); // 获取双数li节点
						Elements eh2s = e2.select("h2"); // 二级节点
						Elements ehuls = e2.select("ul.seacher-label-pare"); // 三级节点
						for (int ieh = 0; ieh < eh2s.size(); ieh++) {
							String level2Name = eh2s.get(ieh).text().trim(); // 二级节点名字
							if ("手机配件".equals(level2Name)) {
								Element ehli = ehuls.get(ieh);
								Elements lias = ehli.select("li a");
								for (Element eh3 : lias) {
									String level3Name = eh3.text(); // 三级节点名字
									String hrefUrl = eh3.attr("href");
									map.put(level3Name, hrefUrl);
								}
							}
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	// 获取配件的code
	public static List<ProductClassfiy> getCode() {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductClassfiyMapper mapper = sqlSession
				.getMapper(ProductClassfiyMapper.class);

		ProductClassfiyExample example = new ProductClassfiyExample();
		example.createCriteria().andPcodeEqualTo("000100020000")
				.andLevelEqualTo(3); //获取配件的类目Code
		List<ProductClassfiy> list = mapper.selectByExample(example);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public static void recursive(int index, String ci, String cf, String url,
			String classCode) {

		logger.info("get list url = " + url + ", classfiyCode =" + classCode);
		String result = MyHttpClient.getMethod(url);
		logger.info(" return listUrlJsonString = " + result);
		if (result.equals("")) { // 如果返回的结果为空，将不再执行
			return;
		}

		Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
				.JSONToObject(result, Map.class);
		List<Map<String, String>> list = (List<Map<String, String>>) hashMap
				.get("goods"); // 当前页面的商品信息
		for (Map<String, String> obj : list) {
			String partnumber = obj.get("partnumber");
			StringBuffer suningurl = new StringBuffer(
					"http://m.suning.com/product/");
			suningurl.append(partnumber).append(".html");
			logger.info("detail url =" + suningurl.toString());

			mobile.setClassfiyCode(classCode); //保存类目Code,入库时使用
			mobile.parseProduct(suningurl.toString()); // 解析入库
		}
		if (index == 0) { // 从头抓取，以后从第一条抓取数据
			String goodsCountStr = (String) hashMap.get("goodsCount");// 获取产品条数
			int goodsCount = Integer.parseInt(goodsCountStr); // 总共多少条产品信息
			int totalPage = (goodsCount + pageNum - 1) / pageNum;// 总页数
			for (int i = 1; i < totalPage; i++) {
				String listUrl = getlistUrl(i, ci, cf);
				recursive(i, ci, cf, listUrl.toString(), classCode);
			}
		}
	}

	public static void main(String[] args) {
		
		Map<String, String> map = getClassUrl(); // 三级节点url规则
		List<ProductClassfiy> list = getCode();  //获取配件的所有类目

		if (args.length == 2) {                 // 断点续传，需要传入 当前页，和 类目Code
			int cp = Integer.parseInt(args[0]); // 当前页
			String classCode = args[1];         // 类目

			ProductClassfiy[] arr = (ProductClassfiy[]) list.toArray();
			int index = 0; // 找到断点的类目的位置
			for (int i = 0; i < arr.length; i++) {
				ProductClassfiy pc = arr[i];
				if (pc.getCode().equals(classCode)) {
					index = i;
					break;
				}
			}

			for (int i = index; i < arr.length; i++) {// 从断点类目的位置开始断点

				ProductClassfiy pc = arr[i];

				String url = map.get(pc.getName());
				url = url.substring(url.lastIndexOf("/") + 1, url.indexOf("."));
				String[] strs = url.split("-");
				String Code = pc.getCode();

				if (Code.equals(classCode)) {
					String urlDetail = getlistUrl(cp, strs[0], strs[1]);

					String result = MyHttpClient
							.getMethod(urlDetail.toString());
					logger.info(" return listUrlJsonString = " + result);
					if (result.equals("")) // 如果返回的结果为空，将不再执行
						return;
					Map<String, Object> hashMap = (Map<String, Object>) JSONUtil
							.JSONToObject(result, Map.class);
					String goodsCountStr = (String) hashMap.get("goodsCount");// 获取总数
					int goodsCount = Integer.parseInt(goodsCountStr); // 总共多少条产品信息
					logger.info("goods Count =" + goodsCountStr);
					int totalPage = (goodsCount + pageNum - 1) / pageNum;// 总页数
					logger.info("totalPage =" + totalPage);

					for (int x = cp; x < totalPage; x++) {
						String xulistUrl = getlistUrl(x, strs[0], strs[1]);
						recursive(x, strs[0], strs[1], xulistUrl.toString(),
								classCode);
					}

				} else {
					String urlDetail = getlistUrl(0, strs[0], strs[1]);
					recursive(0, strs[0], strs[1], urlDetail, Code);
				}

			}
			return;
		}

		for (ProductClassfiy productClassfiy : list) {//从手机配件的第一个项目开始抓取

			String url = map.get(productClassfiy.getName());
			url = url.substring(url.lastIndexOf("/") + 1, url.indexOf("."));
			String[] strs = url.split("-");
			String classCode = productClassfiy.getCode(); //获取类目Code

			String urlDetail = getlistUrl(0, strs[0], strs[1]);
			recursive(0, strs[0], strs[1], urlDetail, classCode);

		}
	}

}
