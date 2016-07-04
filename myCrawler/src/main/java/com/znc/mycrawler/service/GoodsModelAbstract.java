/**   
 * @Title: GoodsModelAbstract.java
 * @Package com.znc.mycrawler.service
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月10日 上午9:43:22
 * @version V1.0   
 */
package com.znc.mycrawler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.znc.mycrawler.dao.ProductClassfiyMapper;
import com.znc.mycrawler.dao.ProductExtraAttrMapper;
import com.znc.mycrawler.dao.ProductImageMapper;
import com.znc.mycrawler.dao.ProductModelExtraAttrMapper;
import com.znc.mycrawler.dao.ProductModelImageMapper;
import com.znc.mycrawler.dao.ProductModelMapper;
import com.znc.mycrawler.dao.SalesPropertiesMapper;
import com.znc.mycrawler.dao.expand.ProductModelMapperExt;
import com.znc.mycrawler.domain.ProductClassfiy;
import com.znc.mycrawler.domain.ProductClassfiyExample;
import com.znc.mycrawler.domain.ProductExtraAttrExample;
import com.znc.mycrawler.domain.ProductExtraAttrWithBLOBs;
import com.znc.mycrawler.domain.ProductImage;
import com.znc.mycrawler.domain.ProductImageExample;
import com.znc.mycrawler.domain.ProductModel;
import com.znc.mycrawler.domain.ProductModelExample;
import com.znc.mycrawler.domain.ProductModelExtraAttrWithBLOBs;
import com.znc.mycrawler.domain.ProductModelImage;
import com.znc.mycrawler.domain.SalesProperties;
import com.znc.mycrawler.util.CommonUtil;
import com.znc.mycrawler.util.MySessionFactory;

/**
 * @ClassName: GoodsModelAbstract
 * @Description: 从商品库到标准库提供的模板方法
 * @author sunlulu
 * @date 2015年4月10日 上午9:43:22
 */

public abstract class GoodsModelAbstract {

	/**
	 * 
	 * @Title: inserProductModel
	 * @Description: 插入商品类型表
	 * @author sunlulu
	 * @param @param productModel
	 * @return void
	 * @throws
	 */
	public void inserProductModel(ProductModel productModel) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelMapper mapper = sqlSession
				.getMapper(ProductModelMapper.class);
		mapper.insert(productModel);
		sqlSession.commit();
		sqlSession.close();
	}

	/**
	 * 
	 * @Title: inserProductModelImage
	 * @Description: 插入商品类型对应的详情页
	 * @author sunlulu
	 * @param @param productModelImage
	 * @return void
	 * @throws
	 */
	public void inserProductModelImage(ProductModelImage productModelImage) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelImageMapper mapper = sqlSession
				.getMapper(ProductModelImageMapper.class);
		mapper.insert(productModelImage);
		sqlSession.commit();
		sqlSession.close();
	}

	/**
	 * 
	 * @Title: inserProductModelImage
	 * @Description: 插入商品类型对应的详情页
	 * @author sunlulu
	 * @param @param list
	 * @return void
	 * @throws
	 */
	public void inserProductModelImage(List<ProductModelImage> list) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelMapperExt mapper = sqlSession
				.getMapper(ProductModelMapperExt.class);
		mapper.insertProductModelImageList(list);
		sqlSession.commit();
		sqlSession.close();
	}

	/**
	 * 
	 * @Title: inserProductModelExtraAttr
	 * @Description: 插入商品类型对应的包装和售后信息
	 * @author sunlulu
	 * @param @param productModelExtraAttrWithBLOBs
	 * @return void
	 * @throws
	 */
	public void inserProductModelExtraAttr(
			ProductModelExtraAttrWithBLOBs productModelExtraAttrWithBLOBs) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelExtraAttrMapper mapper = sqlSession
				.getMapper(ProductModelExtraAttrMapper.class);
		mapper.insert(productModelExtraAttrWithBLOBs);
		sqlSession.commit();
		sqlSession.close();
	}

	/**
	 * 
	 * @Title: getProductImage
	 * @Description: 获取原始商品对应的商品详情页
	 * @author sunlulu
	 * @param @param pid
	 * @param @return
	 * @return List<ProductImage>
	 * @throws
	 */
	public List<ProductImage> getProductImage(String pid) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductImageMapper mapper = sqlSession
				.getMapper(ProductImageMapper.class);

		ProductImageExample example = new ProductImageExample();
		example.createCriteria().andPidEqualTo(pid);
		List<ProductImage> list = mapper.selectByExample(example);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	/**
	 * 
	 * @Title: getProductExtraAttr
	 * @Description: 获取原始商品对应的包装和商品信息
	 * @author sunlulu
	 * @param @param pid
	 * @param @return
	 * @return List<ProductExtraAttrWithBLOBs>
	 * @throws
	 */
	public List<ProductExtraAttrWithBLOBs> getProductExtraAttr(String pid) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductExtraAttrMapper mapper = sqlSession
				.getMapper(ProductExtraAttrMapper.class);

		ProductExtraAttrExample example = new ProductExtraAttrExample();
		example.createCriteria().andPidEqualTo(pid);
		List<ProductExtraAttrWithBLOBs> list = mapper
				.selectByExampleWithBLOBs(example);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	/**
	 * 
	 * @Title: getModelCode
	 * @Description: 根据分级菜单的名称Name和级别Level,获取级别代码
	 * @author sunlulu
	 * @param @param Name
	 * @param @param level
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getClassfiyCode(String Name, int level) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductClassfiyMapper mapper = sqlSession
				.getMapper(ProductClassfiyMapper.class);
		ProductClassfiyExample example = new ProductClassfiyExample();
		example.createCriteria().andNameEqualTo(Name).andLevelEqualTo(level);

		List<ProductClassfiy> list = mapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0).getCode();
		} else
			return null;
	}

	/**
	 * 
	 * @Title: getColumnName
	 * @Description: 根据表名，获取字段名field和备注信息
	 * @author sunlulu
	 * @param @param tableName
	 * @param @return
	 * @return List<Map<String,String>>
	 * @throws
	 */
	public List<Map<String, String>> getColumnName(String tableName) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelMapperExt mapper = sqlSession
				.getMapper(ProductModelMapperExt.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", tableName);
		List<Map<String, String>> list = mapper.getColumnName(map);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	/**
	 * 
	 * @Title: getAttributValue
	 * @Description: 根据输入的表名tableName,字段属性名attribute和分类信息字段品牌brand和型号pmodel
	 * @author sunlulu
	 * @param @param map 例如： key:attribute value:${Table.field}表中字段如color
	 *        key:tableName value:${tableName} 表product_mobile key:brand
	 *        value:${Table.field.value} 表中字段brand对应的值 key:pmodel
	 *        value:${Table.field.value} 表中字段pmodel对应的值
	 * @param @return
	 * @return List<String>
	 * @throws
	 */
	public List<String> getAttributValue(Map<String, String> map) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelMapperExt mapper = sqlSession
				.getMapper(ProductModelMapperExt.class);
		List<String> list = mapper.getAttributeValues(map);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	/**
	 * 
	 * @Title: inserSalesProperties
	 * @Description: 插入销售属性
	 * @author sunlulu
	 * @param @param salesProperties
	 * @return void
	 * @throws
	 */
	public void inserSalesProperties(SalesProperties salesProperties) {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		SalesPropertiesMapper mapper = sqlSession
				.getMapper(SalesPropertiesMapper.class);
		mapper.insert(salesProperties);
		sqlSession.commit();
		sqlSession.close();
	}
	/**
	 * 
	 * @Title: getProductModel 
	 * @Description: 获取所属商品的型号
	 * @author sunlulu
	 * @param @param classfiyCode
	 * @param @return
	 * @return List<ProductModel>  
	 * @throws
	 */
	public List<ProductModel> getProductModel(String classfiyCode) { // 根据商品类型，获取商品型号
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelMapper mapper = sqlSession
				.getMapper(ProductModelMapper.class);

		ProductModelExample example = new ProductModelExample();
		example.createCriteria().andPtypeEqualTo(classfiyCode);
		List<ProductModel> list = mapper.selectByExample(example);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	/**
	 * 
	 * @Title: getMobleList
	 * @Description: 根据不同的产品获取品牌、型号分类列表
	 * @author sunlulu
	 * @param @return
	 * @return List<Map<String,String>>
	 * @throws
	 */
	public List<Map<String, String>> getMobleList() {
		SqlSession sqlSession = MySessionFactory.mySessionFactory.getFactory()
				.openSession();
		ProductModelMapperExt mapper = sqlSession
				.getMapper(ProductModelMapperExt.class);
		Map<String, String> map = new HashMap<String, String>();
		String tableName = this.getTableName();
		map.put("tableName", tableName);
		List<Map<String, String>> list = mapper.getModelByProduct(map);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}
	
	public abstract String getTableName();
	/**
	 * 
	 * @Title: saveProductModelByProducts 
	 * @Description: 保存同商品类型、详情页和包装售后信息
	 * @author sunlulu
	 * @param @param twoLevelName
	 * @return void  
	 * @throws
	 */
	public void saveProductModelByProducts(String levelName,int level) {
		String code = this.getClassfiyCode(levelName, level); // 获取类型编码
		List<Map<String, String>> list = this.getMobleList();// 获取不同商品的型号
		for (Map<String, String> map : list) {
			ProductModel productModel = new ProductModel();
			String pmid = CommonUtil.getId();
			productModel.setId(pmid);
			productModel.setBrand(map.get("brand"));
			productModel.setPmodel(map.get("pmodel"));
			if (map.get("classfiy_code") != null) { // 判断是否已经存在分类
				productModel.setPtype(map.get("classfiy_code"));
			} else {
				productModel.setPtype(code);
			}
			this.inserProductModel(productModel);
			String productId = map.get("id");// 获取一个产品ID
			List<ProductImage> listImage = this.getProductImage(productId);
			List<ProductExtraAttrWithBLOBs> listExtra = this
					.getProductExtraAttr(productId);

			List<ProductModelImage> listModelImages = new ArrayList<ProductModelImage>();
			for (ProductImage productImage : listImage) {
				ProductModelImage productModelImage = new ProductModelImage();
				productModelImage.setPmid(pmid);
				productModelImage.setImageUrl(productImage.getImageUrl());
				listModelImages.add(productModelImage);
				// this.inserProductModelImage(productModelImage);
			}
			if (listModelImages.size() > 0) {
				this.inserProductModelImage(listModelImages);
			}
			for (ProductExtraAttrWithBLOBs productExtraAttrWithBLOBs : listExtra) {
				ProductModelExtraAttrWithBLOBs productModelExtraAttrWithBLOBs = new ProductModelExtraAttrWithBLOBs();
				productModelExtraAttrWithBLOBs.setPmid(pmid);
				productModelExtraAttrWithBLOBs
						.setBoxList(productExtraAttrWithBLOBs.getBoxList());
				productModelExtraAttrWithBLOBs
						.setSoldServer(productExtraAttrWithBLOBs
								.getSoldServer());
				this.inserProductModelExtraAttr(productModelExtraAttrWithBLOBs);
			}

		}
	}

}
