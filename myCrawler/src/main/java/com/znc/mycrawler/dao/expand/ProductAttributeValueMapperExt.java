package com.znc.mycrawler.dao.expand;

import java.util.List;

import com.znc.mycrawler.dao.util.BaseDao;
import com.znc.mycrawler.domain.ProductAttributeValue;

public interface ProductAttributeValueMapperExt extends BaseDao {
	/**
	 * 
	 * @Title: insertProductMobileAccessoryAttr 
	 * @Description: 批量插入属性值表
	 * @author sunlulu
	 * @param @param list
	 * @return void  
	 * @throws
	 */
	void insertProductAttributeValue(List<ProductAttributeValue> list);
}