package com.znc.mycrawler.dao.expand;

import java.util.List;

import com.znc.mycrawler.dao.util.BaseDao;
import com.znc.mycrawler.domain.ProductImage;

public interface ProductImageMapperExt extends BaseDao {
	
	void insertProductImageList(List<ProductImage> list);
}
