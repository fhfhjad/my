package com.znc.mycrawler.dao.expand;

import java.util.List;
import java.util.Map;

import com.znc.mycrawler.dao.util.BaseDao;
import com.znc.mycrawler.domain.ProductModelImage;

public interface ProductModelMapperExt extends BaseDao {
	/**
	 * 
	 * @Title: getModelByProduct 
	 * @Description: TODO
	 * @author sunlulu
	 * @param @param map 包含key:tableName ,value:实际的表名，比如product_moblie
	 * @param @return
	 * @return List<Map<String,String>>  返回根据品牌和类型的列表
	 * 
	 * @throws
	 */
	List<Map<String, String>> getModelByProduct(Map<String, String> map);

	List<Map<String, String>> getColumnName(Map<String, String> map);

	List<String> getAttributeValues(Map<String, String> map);
	
	void insertProductModelImageList(List<ProductModelImage> list);
	
	void updateProduct(Map<String, String> map);
}
