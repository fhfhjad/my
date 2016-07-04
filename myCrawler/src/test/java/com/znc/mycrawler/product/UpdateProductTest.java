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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.dao.ProductMobileMapper;
import com.znc.mycrawler.dao.expand.ProductModelMapperExt;
import com.znc.mycrawler.domain.ProductMobile;
import com.znc.mycrawler.domain.ProductMobileExample;

/**
 * @ClassName: FindRow
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月1日 下午2:18:19
 */

public class UpdateProductTest {

	SqlSessionFactory sessionFactory = null;
	
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
	public void update(){
		SqlSession sqlSession = sessionFactory.openSession();
		ProductModelMapperExt mapper = sqlSession
				.getMapper(ProductModelMapperExt.class);
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("productName", "xxxxxx");
		map.put("id", "ff8080814cc35efb014cc35f01ae0001");
		
		mapper.updateProduct(map);
		
//		ProductImage pimage = new ProductImage();
//		pimage.setId(2L);
//		pimage.setPid("fdlafkd");
//		pimage.setImageUrl("/test/test2.jpg");
//		
//		int a = mapper.insert(pimage);
//		System.out.println("a = " + a);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Ignore
	@Test
	public void getList(){
		SqlSession sqlSession = sessionFactory.openSession();
		ProductMobileMapper mapper = sqlSession
				.getMapper(ProductMobileMapper.class);
		ProductMobileExample example = new ProductMobileExample();
		example.createCriteria();
		List<ProductMobile> list = mapper.selectByExample(example);
		System.out.println(list.size());
	}
	

}
