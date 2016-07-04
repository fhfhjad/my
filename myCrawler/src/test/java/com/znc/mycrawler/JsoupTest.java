/**   
 * @Title: JsoupTest.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月1日 下午2:45:42
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.util.FileUtil;

/**
 * @ClassName: JsoupTest
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月1日 下午2:45:42
 */

public class JsoupTest {
	@Ignore
	@Test
	public void test() throws IOException {
		// 直接从字符串中输入 HTML 文档
		// String html = "<html><head><title> 开源中国社区 </title></head>"
		// + "<body><p> 这里是 jsoup 项目的相关文章 </p></body></html>";
		// Document doc = Jsoup.parse(html);
		// String title = doc.title();
		// System.out.println("doc title = " + title);

		// 从 URL 直接加载 HTML 文档
		// Document doc1 = Jsoup.connect("http://www.oschina.net/").get();
		// String title = doc1.title();
		// System.out.println("doc1 title = " + title);

		Document doc2 = Jsoup.connect("http://www.oschina.net/")
				.data("query", "Java") // 请求参数
				.userAgent("I ’ m jsoup") // 设置 User-Agent
				.cookie("auth", "token") // 设置 cookie
				.timeout(3000) // 设置连接超时时间
				.post(); // 使用 POST 方法访问 URL
		String title = doc2.title();
		System.out.println("doc3 title =" + title);

		// 从文件中加载 HTML 文档
		// File input = new File("D:/test.html");
		// Document doc3 = Jsoup.parse(input, "UTF-8",
		// "http://www.oschina.net/");
	}

	/**
	 * 获取博客上的文章标题和链接
	 */
	@Ignore
	@Test
	public void article() {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
			Elements ListDiv = doc.getElementsByAttributeValue("class",
					"postTitle");
			for (Element element : ListDiv) {
				Elements links = element.getElementsByTag("a");
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text().trim();
					System.out.println(linkHref);
					System.out.println(linkText);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Ignore
	@Test
	public void suning() {// 获取详情信息
		Document doc;
		try {
			// doc =
			// Jsoup.connect("http://m.suning.com/product/126105243.html").get();
			// doc = Jsoup.connect("http://m.suning.com/product/124865606.html")
			doc = Jsoup.connect("http://m.suning.com/product/123129015.html")
					.get();

			Elements listImage = doc.select("div.title h1");
			for (Element element : listImage) {
				System.out.println(element.text());

				Elements allparents = element.parents();
				// for (Element element2 : allparents) {
				// System.out.println("++++++++++++++++++");
				// System.out.println(element2.html());
				// System.out.println("------------------");
				// }
				Element e = allparents.get(allparents.size() - 1);
				System.out.println(e.html());

			}

			// Elements listImage = doc.select("ul.scroller li.item img");
			// for (Element element : listImage) {
			// String imageUrl = element.attr("src");
			// if(imageUrl != null){
			// System.out.println(imageUrl);
			// }
			// String imageDataUrl = element.attr("data-src");
			// if(imageDataUrl != null){
			// System.out.println(imageDataUrl);
			// }
			// }

			// Elements ListDiv = doc.getElementsByAttributeValue("class",
			// "propList");
			//
			// for (Element element : ListDiv) {
			// Elements links = element.getElementsByTag("tr");
			// for (Element link : links) {
			// Elements tds = link.getElementsByTag("td");
			// for (Element td : tds) {
			// // System.out.println(td);
			// System.out.println(td.text().trim()); // 成功获取手机详细信息
			// }
			//
			// }
			// }

			// System.out.println(doc.html());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void getSoldAndService() {// 获取销售信息
		Document doc;
		try {
			doc = Jsoup.connect("http://m.suning.com/product/103413919.html")
					.get();
			Elements ListDiv = doc.select("section:gt(0) div.sectionContent");
			for (Element element : ListDiv) {
				System.out.println(element.html());
			}

			// System.out.println(doc.html());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void getImageUrl() {
		Document doc;
		try {
			// doc = Jsoup.connect("http://m.suning.com/product/124865606.html")
			doc = Jsoup.connect("http://m.suning.com/product/103413919.html")
					.get();

			Elements scripts = doc.getElementsByTag("script");
			for (Element element : scripts) {

				String scriptStr = element.data();
				pattern(scriptStr);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void getImageAndSave() {// 获取图片信息，存储并保存
		Document doc;
		try {
			doc = Jsoup.connect(
					"http://m.suning.com/productDesc/22654909__false.html")
					.get();
			Elements ListDiv = doc.getElementsByTag("img");

			for (Element element : ListDiv) {
				String imageUrl = element.attr("src");
				// FileUtil.savePicToStore("1111", imageUrl);

				System.out
						.println(imageUrl.substring(imageUrl.lastIndexOf("/") + 1));
				String dest = FileUtil.getProductsFilePath("222",
						imageUrl.substring(imageUrl.lastIndexOf("/") + 1));
				System.out.println(dest);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// "productId": "25461354"
	// var descurl = base + "/productDesc/" +
	// sn.productId+"_0070082001_false.html";
	// http://m.suning.com/productDesc/24258629__false.html
	public void pattern(String scriptStr) {

		Pattern p = Pattern.compile("\"productId\": \"\\w*\"");
		Matcher m = p.matcher(scriptStr);
		while (m.find()) {
			StringBuffer descurl = new StringBuffer(
					"http://m.suning.com/productDesc/");
			String str = m.group();
			String[] strs = str.split("\"");
			descurl.append(strs[3]);

			Pattern p2 = Pattern.compile("sn.productId\\+\\S*");
			Matcher m2 = p2.matcher(scriptStr);
			while (m2.find()) {
				String str2 = m2.group();
				String[] strs2 = str2.split("\"");
				descurl.append(strs2[1]);
				System.out.println(descurl.toString());
			}
		}

	}

	@Ignore
	@Test
	public void testPatten() { // 学习用
		Pattern p = Pattern.compile("ab");
		String u = "abcdefsfsaffsabadfewfadfgea";
		Matcher m = p.matcher(u);
		int i = 0;
		while (m.find()) {
			System.out.println(m.group());
			i++;
		}
		System.out.println(i);
	}

	@Ignore
	@Test
	public void test1() { // 学习用
		String s = "sdsdsd<map>asd123</map>21".replaceAll(
				"\\w*<map>|</map>\\w*", "");
		System.out.println(s);
		System.out.println(s);
	}

}
