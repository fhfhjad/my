package com.znc.mycrawler.dao.expand;

import java.util.List;

import com.znc.mycrawler.dao.util.BaseDao;
import com.znc.mycrawler.domain.ProductMobileAccessoryAttr;

public interface ProductMobileAccessoryAttrMapperExt extends BaseDao {
	/**
	 * 
	 * @Title: insertProductMobileAccessoryAttr 
	 * @Description: 批量插入属性值表
	 * @author sunlulu
	 * @param @param list
	 * @return void  
	 * @throws
	 */
	void insertProductMobileAccessoryAttr(List<ProductMobileAccessoryAttr> list);
}