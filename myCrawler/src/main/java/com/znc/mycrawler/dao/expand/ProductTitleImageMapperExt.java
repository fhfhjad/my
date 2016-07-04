package com.znc.mycrawler.dao.expand;

import java.util.List;

import com.znc.mycrawler.dao.util.BaseDao;
import com.znc.mycrawler.domain.ProductTitleImage;

public interface ProductTitleImageMapperExt extends BaseDao {
	
	void insertProductTitleImageList(List<ProductTitleImage> list);
}
