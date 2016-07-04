/**   
 * @Title: SuNingProductType.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月9日 上午10:01:59
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.dao.ProductClassfiyMapper;
import com.znc.mycrawler.domain.ProductClassfiy;
import com.znc.mycrawler.util.MySessionFactory;

/**
 * @ClassName: SuNingProductType
 * @Description: 分类表
 * @author sunlulu
 * @date 2015年4月9日 上午10:01:59
 */

public class SuNingProductType {

	public void saveLevel(ProductClassfiy productClassfiy) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductClassfiyMapper mapper = sqlSession
				.getMapper(ProductClassfiyMapper.class);
		mapper.insert(productClassfiy);
		sqlSession.commit();
		sqlSession.close();
	}

	public String getFormatCode(int level) {
		String le = Integer.toString(level);
		switch (le.length()) {
		case 1:
			return "000" + le;
		case 2:
			return "00" + le;
		case 3:
			return "0" + le;
		case 4:
			return le;
		}
		return "";
	}

	@Ignore
	@Test
	public void suning() {// 获取详情信息
		Document doc;
		try {

			doc = Jsoup.connect("http://m.suning.com/list/list.html").get();

			Elements fisrtLevels = doc.select("ul.sub-searchers-items li"); // 所有的菜单

			int l1 = 1; // 一级菜单编号

			for (int i = 0; i < fisrtLevels.size(); i++) {
				Element e = fisrtLevels.get(i);// 遍历所有li
				Elements eh = e.select("dd h3");// 一级节点
				for (Element element : eh) {

					String level1Name = element.text(); // 一级节点名称
					String code1 = getFormatCode(l1); // 一级节点前四个编码
					String levelcode1 = code1 + "00000000"; // 一级节点的所有编码

					// 保存一级节点
					ProductClassfiy productClassfiy = new ProductClassfiy();
					productClassfiy.setCode(levelcode1);
					productClassfiy.setName(level1Name);
					productClassfiy.setMark(level1Name);
					productClassfiy.setLevel(1);
					this.saveLevel(productClassfiy);

					Element e2 = fisrtLevels.get(i + 1); // 获取双数li节点
					Elements eh2s = e2.select("h2"); // 二级节点
					Elements ehuls = e2.select("ul.seacher-label-pare"); // 三级节点

					int l2 = 1;
					for (int ieh = 0; ieh < eh2s.size(); ieh++) {

						String level2Name = eh2s.get(ieh).text(); // 二级节点名字
						String code2 = this.getFormatCode(l2);
						String levelcode2 = code1 + code2 + "0000";

						productClassfiy = new ProductClassfiy();
						productClassfiy.setCode(levelcode2);
						productClassfiy.setName(level2Name);
						productClassfiy.setMark(level2Name);
						productClassfiy.setPcode(levelcode1);
						productClassfiy.setLevel(2);
						this.saveLevel(productClassfiy);

						Element ehli = ehuls.get(ieh);
						Elements lias = ehli.select("li a");
						int l3 = 1;
						for (Element eh3 : lias) {

							String level3Name = eh3.text(); // 三级节点名字
							String code3 = this.getFormatCode(l3);
							String levelcode3 = code1 + code2 + code3;

							productClassfiy = new ProductClassfiy();
							productClassfiy.setCode(levelcode3);
							productClassfiy.setName(level3Name);
							productClassfiy.setMark(level3Name);
							productClassfiy.setPcode(levelcode2);
							productClassfiy.setLevel(3);
							this.saveLevel(productClassfiy);

							l3++;
						}
						l2++;
					}

					System.out.print("-");

					System.out.println();
					l1++;
				}
			}

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
	public void getClassUrl() {//
		Document doc;
		try {
			doc = Jsoup.connect("http://m.suning.com/list/list.html").get();
			Elements fisrtLevels = doc.select("ul.sub-searchers-items li"); // 所有的菜单
			Map<String, String> map = new HashMap<String, String>();
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
									// System.out.println(level3Name+":"+hrefUrl);
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
	}

}
