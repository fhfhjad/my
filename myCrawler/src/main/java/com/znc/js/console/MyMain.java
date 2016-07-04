/**   
 * @Title: MyMain.java
 * @Package com.znc.js.console
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年6月11日 下午10:55:29
 * @version V1.0   
 */
package com.znc.js.console;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.js.util.JsFunction;

/**
 * @ClassName: MyMain
 * @Description: TODO
 * @author sunlulu
 * @date 2015年6月11日 下午10:55:29
 */

public class MyMain {
	public static void main(String[] args) {

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		Document doc;
		try {
			doc = Jsoup.connect("http://web.jingoal.com/mgt/").get();

			Element listImage = doc.getElementById("token2");
			String token2 = listImage.val();

			String username = "15001068191";
			String pwd = "Fhfhjad1";

			String password = JsFunction.getPasswrod(JsFunction
					.getPasswrod(pwd) + token2);

			Map<String, String> data = new HashMap<String, String>();
			data.put("identify", "");
			data.put("locale", "null");
			data.put("loginName", username);
			data.put("password", password);
			data.put("token2", token2);
			Connection.Response res = Jsoup
					.connect("http://web.jingoal.com/mgt/loginForward.jsp?")
					.data(data).execute();

			Document indoc = res.parse();
			// System.out.println(indoc.html());
			Map<String, String> cookie = res.cookies();
			// res.

			Connection conSearch = Jsoup.connect("http://web.jingoal.com/mgt/")
					.cookies(cookie); // 主页
			// System.out.println(conSearch.get().html());

			Connection rizhi = Jsoup.connect(
					"http://web.jingoal.com/module/worklog/workLogInfo.do")
					.cookies(cookie);// 日志页
			Document zhiDoc = rizhi.get();

			Elements els = zhiDoc.select("#mylogelement tr td.noleft a");

			int i = 0;
			for (Element element : els) {
				System.out.println(element.html(element.html())); // 获取a标签
				// System.out.println(element.attr("onclick")); //获取函数
				i++;
				if (i == 4) {
					Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
					Matcher matcher = pattern.matcher(element.attr("onclick"));
					while (matcher.find()) {
						String parms = matcher.group();
						System.out.println("parms:" + parms);
						String[] strs = parms.replace("'", "").split(",");

						// Map<String,String> pMap = new HashMap<String,
						// String>();
						// pMap.put("workLogId", "51120911");
						// pMap.put("id", "0");
						// pMap.put("type", "2");
						// pMap.put("content", "deploy");
						// String url =
						// "http://web.jingoal.com/module/worklog/add/saveLogSegment.do";
						// cookie.put("cScheme", "http%3A");
						// cookie.put("TOURL",
						// "http%3A%2F%2Fweb.jingoal.com%2Fmodule%2Fworklog%2FlogSegment%2FeditSegmentPre.do");
						// cookie.put("counter", "%5B7126476%2C1%5D");
						// Connection newFile =
						// Jsoup.connect(url).cookies(cookie).data(pMap).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)")
						// .referrer("http://web.jingoal.com/module/worklog/workLogInfo.do").header("Content-Type",
						// "application/json;charset=UTF-8")
						// .ignoreContentType(true);//
						//
						// Document newDoc = newFile.post();
						// System.out.println(newDoc.html());
					}
				}

			}

			// System.out.println(rizhi.get().html());

			Map<String, String> myData = new HashMap<String, String>();
			myData.put("workLogId", "51120911");
			myData.put("id", "59520311");
			myData.put("type", "2");
			myData.put("content", "my test");
			Connection test = Jsoup
					.connect(
							"http://web.jingoal.com/module/worklog/modify/saveLogSegment.do")
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:38.0) Gecko/20100101 Firefox/38.0")
					.header("Content-Type", "application/json;charset=UTF-8");
			test.data(myData).cookies(cookie);

			// Iterator<Entry<String, String>> iterCookie =
			// cookie.entrySet().iterator();
			// while(iterCookie.hasNext()){
			// Entry<String, String> entry = iterCookie.next();
			// System.out.println(entry.getKey() +":"+ entry.getValue());
			// rizhi.cookie(entry.getKey(), entry.getValue());
			// }
			Document xdoc = test.post();

			System.out.println(xdoc.html());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
