/**   
 * @Title: FindRow.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月1日 下午2:18:19
 * @version V1.0   
 */
package com.znc.mycrawler.productaccessory;

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

import com.znc.mycrawler.dao.ProductMobileAccessoryAttrMapper;
import com.znc.mycrawler.dao.ProductMobileAccessoryMapper;
import com.znc.mycrawler.dao.expand.ProductMobileAccessoryAttrMapperExt;
import com.znc.mycrawler.domain.ProductMobileAccessory;
import com.znc.mycrawler.domain.ProductMobileAccessoryAttr;
import com.znc.mycrawler.domain.ProductMobileAccessoryAttrExample;


public class InsertProductMoblieAccessoryTest {
	
	private static Logger logger = Logger.getLogger(InsertProductMoblieAccessoryTest.class);

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
	@Ignore
	@Test
	public void curdProductMobileAccessory(){
		SqlSession sqlSession = sessionFactory.openSession();
		ProductMobileAccessoryMapper mapper = sqlSession
				.getMapper(ProductMobileAccessoryMapper.class);
		//插入
		ProductMobileAccessory productMobileAccessory = new ProductMobileAccessory();
		productMobileAccessory.setId(id);
		productMobileAccessory.setClassfiyCode("xxx");
		productMobileAccessory.setPmodel("iPhone4S");
		productMobileAccessory.setProductName("测试");
		productMobileAccessory.setBrand("苹果");
		productMobileAccessory.setDrawenable(1);
		productMobileAccessory.setSuningNumber("0912837");
		
		mapper.insert(productMobileAccessory);
		//查看
		
		productMobileAccessory = mapper.selectByPrimaryKey(id);
		logger.info(new ReflectionToStringBuilder(productMobileAccessory));
		
		//删除
		mapper.deleteByPrimaryKey(id);
		
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Ignore
	@Test
	public void curdProductMobileAccessoryAttr(){
		SqlSession sqlSession = sessionFactory.openSession();
		
		ProductMobileAccessoryAttrMapper mapper = sqlSession
				.getMapper(ProductMobileAccessoryAttrMapper.class);
		ProductMobileAccessoryAttrMapperExt mapperExt = sqlSession
				.getMapper(ProductMobileAccessoryAttrMapperExt.class);
		
		//批量插入
		List<ProductMobileAccessoryAttr> list = new ArrayList<ProductMobileAccessoryAttr>();
		ProductMobileAccessoryAttr productMobileAccessoryAttr = new ProductMobileAccessoryAttr();
		productMobileAccessoryAttr.setPmaid(id);
		productMobileAccessoryAttr.setAttributeName("品牌");
		productMobileAccessoryAttr.setAttributeValue("苹果");
		productMobileAccessoryAttr.setClassfiyCode("xxxxx");
		list.add(productMobileAccessoryAttr);
		
		ProductMobileAccessoryAttr productMobileAccessoryAttr1 = new ProductMobileAccessoryAttr();
		productMobileAccessoryAttr1.setPmaid(id);
		productMobileAccessoryAttr1.setAttributeName("型号");
		productMobileAccessoryAttr1.setAttributeValue("iPhone4S");
		productMobileAccessoryAttr1.setClassfiyCode("xxx");
		list.add(productMobileAccessoryAttr1);
		mapperExt.insertProductMobileAccessoryAttr(list);
		
		
		//查询
		ProductMobileAccessoryAttrExample example = new ProductMobileAccessoryAttrExample();
		example.createCriteria().andPmaidEqualTo(id);
		List<ProductMobileAccessoryAttr> show = mapper.selectByExample(example);
		
		for (ProductMobileAccessoryAttr productMobileAccessoryAttr2 : show) {
			logger.info(new ReflectionToStringBuilder(productMobileAccessoryAttr2));
		}
		//删除
		for (ProductMobileAccessoryAttr productMobileAccessoryAttr2 : show) {
			mapper.deleteByPrimaryKey(productMobileAccessoryAttr2.getId());
		}
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	

}
