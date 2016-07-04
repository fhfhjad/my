/**   
 * @Title: UpdateMobleName.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月22日 下午3:36:44
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.mycrawler.dao.ProductPadMapper;
import com.znc.mycrawler.dao.expand.ProductModelMapperExt;
import com.znc.mycrawler.domain.ProductMobile;
import com.znc.mycrawler.domain.ProductPad;
import com.znc.mycrawler.domain.ProductPadExample;

/**
 * @ClassName: UpdateMobleName
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月22日 下午3:36:44
 */

public class UpdateMobleName {
	
	public static SqlSessionFactory sessionFactory = null;
	
//	public static int pageNum = 100; //默认每页显示20条记录
	
	public static Logger logger = Logger.getLogger(UpdateMobleName.class);
	
	public static void init() {
		String resource = "mybatisConfig.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static  String getProductName(String suningNumber){
		Document doc;
		StringBuffer sb = new StringBuffer("http://m.suning.com/product/");
		sb.append(suningNumber).append(".html");
		logger.info("url : " + sb.toString());
		String productName = null;
		try {
			doc = Jsoup.connect(sb.toString()).get();
			Elements listImage = doc.select("div.title h1");
			for (Element element : listImage) {
				productName = element.text();
			}
			return productName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productName;
		
	}

	public static void main(String[] args) {
		init();
		try {
			
			SqlSession sqlSession = sessionFactory.openSession();
			ProductPadMapper mapper = sqlSession
					.getMapper(ProductPadMapper.class);
			ProductPadExample example = new ProductPadExample();
			example.createCriteria();
			List<ProductPad> list = mapper.selectByExample(example);
			
			
			int i = 0;
			int num = list.size();
			for (ProductPad productPad : list) {
				
				if(productPad.getProductName() == null){
					String productName = getProductName(productPad.getSuningNumber());
					productPad.setProductName(productName);
					mapper.updateByPrimaryKey(productPad);
//					System.out.println(productPad.getId());
				}
				
				logger.info(++i + " of "+num+" record updated");
			}
			
			sqlSession.commit();
			sqlSession.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
