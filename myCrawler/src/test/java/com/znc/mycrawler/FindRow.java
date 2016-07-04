/**   
 * @Title: FindRow.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月1日 下午2:18:19
 * @version V1.0   
 */
package com.znc.mycrawler;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.dao.ProductImageMapper;
import com.znc.mycrawler.domain.ProductImage;

/**
 * @ClassName: FindRow
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月1日 下午2:18:19
 */

public class FindRow {

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
	public void test() {
		SqlSession sqlSession = sessionFactory.openSession();
		ProductImageMapper mapper = sqlSession
				.getMapper(ProductImageMapper.class);

		ProductImage pimage = mapper.selectByPrimaryKey(2L);

		System.out.println(pimage.getImageUrl());
		System.out.println(pimage.getPid());
	}
	@Ignore
	@Test
	public void insert(){
		SqlSession sqlSession = sessionFactory.openSession();
		ProductImageMapper mapper = sqlSession
				.getMapper(ProductImageMapper.class);
		
		ProductImage pimage = new ProductImage();
		pimage.setId(2L);
		pimage.setPid("fdlafkd");
		pimage.setImageUrl("/test/test2.jpg");
		
		int a = mapper.insert(pimage);
		System.out.println("a = " + a);
		sqlSession.commit();
		sqlSession.close();
	}
	

}
