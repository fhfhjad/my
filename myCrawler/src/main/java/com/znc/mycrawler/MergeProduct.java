/**   
 * @Title: MergeProduct.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月25日 下午1:35:26
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.mycrawler.dao.ProductAttributeValueMapper;
import com.znc.mycrawler.dao.ProductMapper;
import com.znc.mycrawler.dao.expand.ProductAttributeValueMapperExt;
import com.znc.mycrawler.domain.Product;
import com.znc.mycrawler.domain.ProductAttributeValue;
import com.znc.mycrawler.domain.ProductAttributeValueExample;
import com.znc.mycrawler.domain.ProductExample;
import com.znc.mycrawler.domain.ProductMobile;

/**
 * @ClassName: MergeProduct
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月25日 下午1:35:26
 */

public class MergeProduct {

public static SqlSessionFactory sessionFactory = null;
	
	
	public static Logger logger = Logger.getLogger(MergeProduct.class);
	
	public static void init() {
		String resource = "mybatisConfig.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean haveRows(String pid) {
		SqlSession sqlSession = sessionFactory.openSession();
		ProductAttributeValueMapper mapper = sqlSession
				.getMapper(ProductAttributeValueMapper.class);

		ProductAttributeValueExample example = new ProductAttributeValueExample();
		example.createCriteria().andPidEqualTo(pid);
		List<ProductAttributeValue> list = mapper.selectByExample(example);
		sqlSession.close();
		if (list != null && list.size() != 0) {
			return true;
		}
		return false;
	}
	
	public static void setProductAtrribut(Product p) throws InterruptedException{
		
		if(haveRows(p.getId())){
			return;
		}
		Thread.sleep(100L);
		
		Document doc;
		StringBuffer sb = new StringBuffer("http://m.suning.com/product/");
		sb.append(p.getSuningNumber()).append(".html");
		logger.info("url : " + sb.toString());
		
		try {
			doc = Jsoup.connect(sb.toString()).get();
			Elements ListDiv = doc.getElementsByAttributeValue("class",
					"propList");
			for (Element element : ListDiv) {
				insertHistoryProductFromElement(element, p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertHistoryProductFromElement(Element element,
			Product product) {
		
		List<ProductAttributeValue> listAttr = new ArrayList<ProductAttributeValue>();
		Elements links = element.getElementsByTag("tr");
		for (Element link : links) {
			Elements tds = link.getElementsByTag("td");
			// 设置属性值
			ProductAttributeValue productMobileAccessoryAttr = new ProductAttributeValue();
			productMobileAccessoryAttr.setPid(product.getId());
			productMobileAccessoryAttr.setClassfiyCode(product.getClassfiyCode()); 
			strToObjectAttr(productMobileAccessoryAttr, tds);
			
			listAttr.add(productMobileAccessoryAttr);
		}
		if(listAttr.size() > 0)
			insertHistoryProductAttr(listAttr);          //插入属性值
	}
	private static void insertHistoryProductAttr(
			List<ProductAttributeValue> listAttr) {
		SqlSession sqlSession =  sessionFactory.openSession();
		ProductAttributeValueMapperExt mapper = sqlSession
				.getMapper(ProductAttributeValueMapperExt.class);
		mapper.insertProductAttributeValue(listAttr);
		sqlSession.commit();
		sqlSession.close();
	}


	private static void strToObjectAttr(
			ProductAttributeValue productMobileAccessoryAttr, Elements tds) {
		if (tds.size() == 2) {
			String key = tds.get(0).text().trim();
			String value = tds.get(1).text().trim();
			productMobileAccessoryAttr.setAttributeName(key);
			productMobileAccessoryAttr.setAttributeValue(value);
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		init();
		SqlSession sqlSession = sessionFactory.openSession();
		ProductMapper mapper = sqlSession
				.getMapper(ProductMapper.class);
		ProductExample example = new ProductExample();
		example.createCriteria();
		
		List<Product> list = mapper.selectByExample(example);
		int num = list.size();
		int i = 0;
		for (Product product : list) {
			setProductAtrribut(product);
			logger.info(i++ +" of " +num + "finished!");
		}
		
		sqlSession.commit();
		sqlSession.close();

	}

}
