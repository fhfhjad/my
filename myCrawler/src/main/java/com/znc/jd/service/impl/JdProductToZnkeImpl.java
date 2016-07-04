package com.znc.jd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.znc.jd.dao.JdCategoryMapper;
import com.znc.jd.dao.JdGoodsAttrvalMapper;
import com.znc.jd.dao.JdGoodsImageMapper;
import com.znc.jd.dao.JdGoodsMapper;
import com.znc.jd.dao.PProductAttributeValueMapper;
import com.znc.jd.dao.PProductImageGroupMapper;
import com.znc.jd.dao.PProductTitleImageMapper;
import com.znc.jd.dao.PShopBrandMapper;
import com.znc.jd.dao.PShopCategoryMapper;
import com.znc.jd.dao.PShopProductMapper;
import com.znc.jd.domain.JdCategory;
import com.znc.jd.domain.JdCategoryExample;
import com.znc.jd.domain.JdGoods;
import com.znc.jd.domain.JdGoodsAttrval;
import com.znc.jd.domain.JdGoodsAttrvalExample;
import com.znc.jd.domain.JdGoodsExample;
import com.znc.jd.domain.JdGoodsImage;
import com.znc.jd.domain.JdGoodsImageExample;
import com.znc.jd.domain.PProductAttributeValue;
import com.znc.jd.domain.PProductImageGroup;
import com.znc.jd.domain.PProductTitleImage;
import com.znc.jd.domain.PShopBrand;
import com.znc.jd.domain.PShopBrandExample;
import com.znc.jd.domain.PShopCategory;
import com.znc.jd.domain.PShopCategoryExample;
import com.znc.jd.domain.PShopProduct;
import com.znc.jd.domain.PShopProductExample;
import com.znc.jd.service.DbAbstract;
import com.znc.mycrawler.util.CommonUtil;

/**
 * 将抓取到的京东数据转移到znke的产品库中
 * @author zhj
 *
 */
public class JdProductToZnkeImpl extends DbAbstract{
	
	public static Logger logger = Logger.getLogger(JdProductToZnkeImpl.class);
	
	/**
	 * 获取智能客分类（p_shop_category）
	 * @return
	 */
	public List<PShopCategory> getAllPShopCategory(){
		List<PShopCategory> list = new ArrayList<PShopCategory>();
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		
		PShopCategoryMapper mapper = sqlSession.getMapper(PShopCategoryMapper.class);
		
		PShopCategoryExample example = new PShopCategoryExample();
//		example.createCriteria().andCategoryIdIsNotNull();
		example.createCriteria().andDepthEqualTo(2);
		
		list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		
		return list;
	}
	
	/**
	 * 根据智能客分类名字获取京东分类数据
	 * @param znkeCategoryName
	 * @return
	 */
	public JdCategory getJdCategory(String znkeCategoryName){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdCategoryMapper mapper = sqlSession.getMapper(JdCategoryMapper.class);
		JdCategoryExample example = new JdCategoryExample();
		example.createCriteria().andZnkeCategoryNameEqualTo(znkeCategoryName);
		
		List<JdCategory> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 根据京东分类ID获取京东产品数据
	 * @param categoryId
	 * @return
	 */
	public List<JdGoods> getAllJdGoods(Long categoryId){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsMapper mapper = sqlSession.getMapper(JdGoodsMapper.class);
		JdGoodsExample example = new JdGoodsExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		
		List<JdGoods> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		return list;
	}
	
	/**
	 * 根据京东分类ID获取京东产品数据
	 * @param categoryId
	 * @return
	 */
	public List<JdGoods> getJdSerielGoods(String goodsSeriel){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsMapper mapper = sqlSession.getMapper(JdGoodsMapper.class);
		JdGoodsExample example = new JdGoodsExample();
		example.createCriteria().andGoodsSerielEqualTo(goodsSeriel);
		
		List<JdGoods> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		return list;
	}
	
	/**
	 * 根据JD商品goodsSeriel判断是否已存在该数据
	 * @param goodsId
	 * @return
	 */
	public boolean getPShopProduct(String goodsSeriel){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PShopProductMapper mapper = sqlSession.getMapper(PShopProductMapper.class);
		PShopProductExample example = new PShopProductExample();
		example.createCriteria().andSuningNumberEqualTo(goodsSeriel);
		
		List<PShopProduct> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		if (list != null && list.size() != 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 根据JD品牌名称获取ZNKE品牌对象信息
	 * @param brandName
	 * @return
	 */
	public PShopBrand getPShopBrand(String brandName){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PShopBrandMapper mapper = sqlSession.getMapper(PShopBrandMapper.class);
		PShopBrandExample example = new PShopBrandExample();
		example.createCriteria().andNameLike(brandName);
		
		List<PShopBrand> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 根据jd商品ID获取商品属性列表
	 * @param goodsId
	 * @return
	 */
	public List<JdGoodsAttrval> getJdGoodsAttrvalByGoodsId(Long goodsId){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsAttrvalMapper mapper = sqlSession.getMapper(JdGoodsAttrvalMapper.class);
		JdGoodsAttrvalExample example = new JdGoodsAttrvalExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		
//		List<JdGoodsAttrval> list = mapper.selectByExample(example);
		List<JdGoodsAttrval> list = mapper.selectByExampleWithBLOBs(example);
		closeSqlSession(sqlSession);
		return list;
	}
	
	/**
	 * 根据jd商品ID获取商品图片列表
	 * @param goodsId
	 * @return
	 */
	public List<JdGoodsImage> getJdGoodsImageByGoodsId(Long goodsId){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsImageMapper mapper = sqlSession.getMapper(JdGoodsImageMapper.class);
		JdGoodsImageExample example = new JdGoodsImageExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		
		List<JdGoodsImage> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		return list;
	}
	
	
	/**
	 * 插入数据到p_shop_product
	 * @param product
	 */
	public void insertPShopProduct(PShopProduct product){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PShopProductMapper mapper = sqlSession.getMapper(PShopProductMapper.class);
		mapper.insert(product);
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 插入数据到p_product_attribute_value
	 * @param attribute
	 */
	public void insertPProductAttributeValue(PProductAttributeValue attribute){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PProductAttributeValueMapper mapper = sqlSession.getMapper(PProductAttributeValueMapper.class);
		mapper.insert(attribute);
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 插入数据到p_product_image_group
	 * @param ppig
	 */
	public void insertPProductImageGroup(PProductImageGroup ppig){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PProductImageGroupMapper mapper = sqlSession.getMapper(PProductImageGroupMapper.class);
		mapper.insert(ppig);
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 插入数据到p_product_title_image
	 * @param ppig
	 */
	public void insertPProductTitleImage(PProductTitleImage ppti){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		PProductTitleImageMapper mapper = sqlSession.getMapper(PProductTitleImageMapper.class);
		mapper.insert(ppti);
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 1.获取所有智能客分类数据
	 * 2.遍历步骤1中获得的分类数据，使用分类名称作为查询条件查询抓取到的京东数据列表
	 * 3.遍历步骤2中获得的京东数据，插入到智能客数据库产品的相关表中
	 */
	public void moveJdDataToZnke(){
		
		logger.info("------------start move data---------------");
		List<PShopCategory> pShopCategoryList = this.getAllPShopCategory();
		
		if(!pShopCategoryList.isEmpty()){
			for (PShopCategory pShopCategory : pShopCategoryList) {
				String znkeCategoryName = pShopCategory.getName();
				
				JdCategory jdCategory = this.getJdCategory(znkeCategoryName);
				if(null != jdCategory){
					logger.info("start process category:" + znkeCategoryName);
					try {
						this.processJdGoodsData(pShopCategory, jdCategory);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 处理JD产品数据
	 * @param jdCategory
	 */
	public void processJdGoodsData(PShopCategory pShopCategory, JdCategory jdCategory){
		
		Long znkeCategoryId = pShopCategory.getCategoryId();
		Long pTypeId = pShopCategory.getPtypeId();
		String znkeCategoryPath = pShopCategory.getPath();
		
		List<JdGoods> jdGoodsList = this.getAllJdGoods(jdCategory.getId());
		
		for (JdGoods jdGoods : jdGoodsList) {
			
			Long goodsId = jdGoods.getGoodsId();
			String pid = CommonUtil.getId();;
			
			if(!this.getPShopProduct(jdGoods.getGoodsSeriel())){
				//不存在
				PShopProduct pShopProduct = new PShopProduct();
				
				pShopProduct.setPid(pid);
				pShopProduct.setPtypeId(pTypeId);
				
				String jdBrand = jdGoods.getGoodsBrand();
				if(!"".equals(jdBrand) && null != jdBrand){
					
					jdBrand = jdBrand.replaceAll("[^\u4e00-\u9fa5]","");
					
					PShopBrand brand = this.getPShopBrand(jdBrand);
					
					if(null != brand){
						pShopProduct.setBrandId(brand.getBrandId());
					}
				}
				
				pShopProduct.setCategoryId(znkeCategoryId);
				pShopProduct.setCategoryPath(znkeCategoryPath);
				pShopProduct.setName(jdGoods.getGoodsName());
				
				pShopProduct.setStatus(3L);
				//记录是否已储存过该数据
				pShopProduct.setSuningNumber(jdGoods.getGoodsSeriel());
				
//				pShopProduct.setIsColorsize(false);
				this.insertPShopProduct(pShopProduct);
				
				//商品属性
				List<JdGoodsAttrval> jdGoodsAttrvalList = this.getJdGoodsAttrvalByGoodsId(goodsId);
				for (JdGoodsAttrval jdGoodsAttrval : jdGoodsAttrvalList) {
					PProductAttributeValue ppav = new PProductAttributeValue();
					ppav.setPid(pid);
					ppav.setAttributeName(jdGoodsAttrval.getGoodsAttr());
					
//					logger.info("jdattribute-->>" + jdGoodsAttrval.getGoodsVal());
					ppav.setAttributeValue(jdGoodsAttrval.getGoodsVal());
					
					this.insertPProductAttributeValue(ppav);
				}
				
				List<JdGoods> jdSerielList = this.getJdSerielGoods(jdGoods.getGoodsSeriel());
				int groupCount = 1;
				for (JdGoods seriel : jdSerielList) {
					//商品图片
					PProductImageGroup ppig = new PProductImageGroup();
					ppig.setPid(pid);
					ppig.setName(jdGoods.getGoodsName() + "-图片组-" + groupCount++);
					this.insertPProductImageGroup(ppig);
					
					Long imageGroupId = ppig.getImageGroupid();
					
					List<JdGoodsImage> jdGoodsImageList = this.getJdGoodsImageByGoodsId(seriel.getGoodsId());
					int imageCount = 0;
					for (JdGoodsImage jdGoodsImage : jdGoodsImageList) {
						PProductTitleImage ppti = new PProductTitleImage();
						ppti.setImageGroupId(imageGroupId);
						ppti.setImageUrl(jdGoodsImage.getGoodsUrl().replace("/home/znc_znke/", ""));
						ppti.setIsDefault("0");
						
						this.insertPProductTitleImage(ppti);
						imageCount++;
						if(imageCount==6)
							break;
					}
				}
				
			}
		}
	}
}
