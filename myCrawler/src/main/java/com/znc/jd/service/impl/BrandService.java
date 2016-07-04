package com.znc.jd.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;

import com.znc.jd.dao.JdCategoryMapper;
import com.znc.jd.dao.PShopBrandMapper;
import com.znc.jd.domain.JdCategory;
import com.znc.jd.domain.PShopBrand;
import com.znc.jd.domain.PShopBrandExample;
import com.znc.jd.service.DbAbstract;
import com.znc.mycrawler.util.PinyinUtil;

public class BrandService extends DbAbstract {
	
	/**
	 * 更新jd_category的数据
	 * @param jdCategory
	 */
	public void updateBrand(PShopBrand jdCategory){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PShopBrandMapper mapper = sqlSession.getMapper(PShopBrandMapper.class);
		mapper.updateByPrimaryKey(jdCategory);
		closeSqlSession(sqlSession);
	}
	
	public  List<PShopBrand> queryBrand(){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PShopBrandMapper mapper = sqlSession.getMapper(PShopBrandMapper.class);
		PShopBrandExample example = new PShopBrandExample();
		List<PShopBrand> result = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		return result;
	}
	
	
	

}
