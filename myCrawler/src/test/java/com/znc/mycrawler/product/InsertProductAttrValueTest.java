/**   
 * @Title: FindRow.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月1日 下午2:18:19
 * @version V1.0   
 */
package com.znc.mycrawler.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.dao.ProductAttributeValueMapper;
import com.znc.mycrawler.dao.expand.ProductAttributeValueMapperExt;
import com.znc.mycrawler.domain.ProductAttributeValue;
import com.znc.mycrawler.domain.ProductAttributeValueExample;
import com.znc.mycrawler.domain.ProductMobileAccessoryAttr;


public class InsertProductAttrValueTest {
	
	private static Logger logger = Logger.getLogger(InsertProductAttrValueTest.class);

	SqlSessionFactory sessionFactory = null;
	
	private static String id = "test";
	
	@Ignore
	@Before
	public void init() {
		String resource = "mybatisConfig.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void curdProductMobileAccessoryAttr(){
		SqlSession sqlSession = sessionFactory.openSession();
		
		ProductAttributeValueMapper mapper = sqlSession
				.getMapper(ProductAttributeValueMapper.class);
		ProductAttributeValueMapperExt mapperExt = sqlSession
				.getMapper(ProductAttributeValueMapperExt.class);
		
		//批量插入
		List<ProductAttributeValue> list = new ArrayList<ProductAttributeValue>();
		ProductAttributeValue productMobileAccessoryAttr = new ProductAttributeValue();
		productMobileAccessoryAttr.setPid(id);
		productMobileAccessoryAttr.setAttributeName("品牌");
		productMobileAccessoryAttr.setAttributeValue("苹果");
		productMobileAccessoryAttr.setClassfiyCode("xxxxx");
		list.add(productMobileAccessoryAttr);
		
		ProductAttributeValue productMobileAccessoryAttr1 = new ProductAttributeValue();
		productMobileAccessoryAttr1.setPid(id);
		productMobileAccessoryAttr1.setAttributeName("型号");
		productMobileAccessoryAttr1.setAttributeValue("iPhone4S");
		productMobileAccessoryAttr1.setClassfiyCode("xxx");
		list.add(productMobileAccessoryAttr1);
		mapperExt.insertProductAttributeValue(list);;
		
		
		//查询
		ProductAttributeValueExample example = new ProductAttributeValueExample();
		example.createCriteria().andPidEqualTo(id);
		List<ProductAttributeValue> show = mapper.selectByExample(example);
		
		for (ProductAttributeValue productMobileAccessoryAttr2 : show) {
			logger.info(new ReflectionToStringBuilder(productMobileAccessoryAttr2));
		}
		//删除
		for (ProductAttributeValue productMobileAccessoryAttr2 : show) {
			mapper.deleteByPrimaryKey(productMobileAccessoryAttr2.getId());
		}
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	

}
