/**   
 * @Title: HistoryMobileAbstract.java
 * @Package com.znc.mycrawler.service
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月1日 下午6:37:28
 * @version V1.0   
 */
package com.znc.mycrawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.mycrawler.dao.ProductExtraAttrMapper;
import com.znc.mycrawler.dao.ProductImageMapper;
import com.znc.mycrawler.dao.expand.ProductImageMapperExt;
import com.znc.mycrawler.dao.expand.ProductTitleImageMapperExt;
import com.znc.mycrawler.domain.ProductExtraAttrWithBLOBs;
import com.znc.mycrawler.domain.ProductImage;
import com.znc.mycrawler.domain.ProductTitleImage;
import com.znc.mycrawler.util.CommonUtil;
import com.znc.mycrawler.util.FileUtil;
import com.znc.mycrawler.util.MySessionFactory;

/**
 * @ClassName: HistoryMobileAbstract
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月1日 下午6:37:28
 */

public abstract class HistoryAbstract<T> implements HistoryInter<T> {

	private static Logger logger = Logger.getLogger(HistoryAbstract.class);

	/**
	 * 
	 * @Title: getSqlSessionFactory
	 * @Description: 公共方法，获取SqlSessionFactory
	 * @author sunlulu
	 * @param @return
	 * @return SqlSessionFactory
	 * @throws
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		return MySessionFactory.mySessionFactory.getFactory();
	}

	public void insertHistorySoldService(
			ProductExtraAttrWithBLOBs productExtraAttrWithBLOBs) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductExtraAttrMapper mapper = sqlSession
				.getMapper(ProductExtraAttrMapper.class);
		mapper.insert(productExtraAttrWithBLOBs);
		sqlSession.commit();
		sqlSession.close();

	}

	public void insertHistoryImage(ProductImage productImage) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductImageMapper mapper = sqlSession
				.getMapper(ProductImageMapper.class);
		mapper.insert(productImage);
		sqlSession.commit();
		sqlSession.close();
	}

	public void insertHistoryImage(List<ProductImage> list) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductImageMapperExt mapper = sqlSession
				.getMapper(ProductImageMapperExt.class);
		mapper.insertProductImageList(list);
		sqlSession.commit();
		sqlSession.close();
	}

	public void insertHistoryTitleImage(List<ProductTitleImage> list) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductTitleImageMapperExt mapper = sqlSession
				.getMapper(ProductTitleImageMapperExt.class);
		mapper.insertProductTitleImageList(list);
		sqlSession.commit();
		sqlSession.close();
	}

	/**
	 * 
	 * @Title: getProductDescUrl
	 * @Description: 从js中获取详情图片链接
	 * @author sunlulu
	 * @param @param scriptStr
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getProductDescUrl(String scriptStr) {
		Pattern p = Pattern.compile("\"productId\": \"\\w*\"");
		Matcher m = p.matcher(scriptStr);
		while (m.find()) {
			StringBuffer descurl = new StringBuffer(
					"http://m.suning.com/productDesc/");
			String str = m.group();
			String[] strs = str.split("\"");
			descurl.append(strs[3]);

			Pattern p2 = Pattern.compile("sn.productId\\+\\S*");
			Matcher m2 = p2.matcher(scriptStr);
			while (m2.find()) {
				String str2 = m2.group();
				String[] strs2 = str2.split("\"");
				descurl.append(strs2[1]);
				return descurl.toString();
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: saveProductTitle
	 * @Description: 仅仅获取产品滚动的图片信息（临时方法）
	 * @author sunlulu
	 * @param
	 * @return void
	 * @throws
	 */
	public void saveProductTitle(String url, String productId) {

		Document doc;
		try {
			doc = Jsoup.connect(url).get();

			// 获取滚动图片信息
			Elements listImage = doc.select("ul.scroller li.item img");
			List<ProductTitleImage> lists = new ArrayList<ProductTitleImage>();
			for (Element element : listImage) {

				String imageUrl = element.attr("src");
				if (imageUrl != null && imageUrl.length() != 0) {
					ProductTitleImage productTitleImage = new ProductTitleImage();
					productTitleImage.setPid(productId);

					productTitleImage.setImageUrl(FileUtil.getProductsFilePath(
							productId,
							imageUrl.substring(imageUrl.lastIndexOf("/") + 1)));
					FileUtil.savePicToStore(productId, imageUrl);
					lists.add(productTitleImage);
				}
				String imageDataUrl = element.attr("data-src");
				if (imageDataUrl != null && imageDataUrl.length() != 0) {
					ProductTitleImage productTitleImage = new ProductTitleImage();
					productTitleImage.setPid(productId);

					productTitleImage.setImageUrl(FileUtil.getProductsFilePath(
							productId, imageDataUrl.substring(imageDataUrl
									.lastIndexOf("/") + 1)));
					FileUtil.savePicToStore(productId, imageDataUrl);
					lists.add(productTitleImage);
				}

			}
			insertHistoryTitleImage(lists);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("save productUrl error " + e.getMessage());

		}
	}

	/**
	 * 解析单个html页面
	 */
	public void parseProduct(String url) {

		String urlNum = url.substring(url.lastIndexOf("/") + 1,
				url.lastIndexOf("."));
		if (haveRows(urlNum)) {
			// ProductMobile p = this.getProductMobile(urlNum);
			// this.saveProductTitle(url, p.getId());
			return;// 如果已经抓取返回
		}

		Document doc;
		try {
			doc = Jsoup.connect(url).get();

			String productId = CommonUtil.getId();// 不重复的32位字符串

			// 1.获取原始数据信息
			Elements ListDiv = doc.getElementsByAttributeValue("class",
					"propList");

			for (Element element : ListDiv) {
				insertHistoryProductFromElement(element, productId, urlNum);
			}

			// 1.1获取滚动图片信息
			Elements listImage = doc.select("ul.scroller li.item img");
			List<ProductTitleImage> lists = new ArrayList<ProductTitleImage>();
			for (Element element : listImage) {

				ProductTitleImage productTitleImage = new ProductTitleImage();
				productTitleImage.setPid(productId);
				String imageUrl = element.attr("src");
				if (imageUrl != null && imageUrl.length() != 0) {
					productTitleImage.setImageUrl(FileUtil.getProductsFilePath(
							productId,
							imageUrl.substring(imageUrl.lastIndexOf("/") + 1)));
					FileUtil.savePicToStore(productId, imageUrl);
					lists.add(productTitleImage);
				}
				String imageDataUrl = element.attr("data-src");
				if (imageDataUrl != null && imageDataUrl.length() != 0) {
					productTitleImage.setImageUrl(FileUtil.getProductsFilePath(
							productId, imageDataUrl.substring(imageDataUrl
									.lastIndexOf("/") + 1)));
					FileUtil.savePicToStore(productId, imageDataUrl);
					lists.add(productTitleImage);
				}

			}
			insertHistoryTitleImage(lists);

			// 2.获取包装和服务信息
			Elements soldAndServices = doc
					.select("section:gt(0) div.sectionContent");
			if (soldAndServices.size() > 0) {
				ProductExtraAttrWithBLOBs productExtraAttrWithBLOBs = new ProductExtraAttrWithBLOBs();
				productExtraAttrWithBLOBs.setPid(productId);
				productExtraAttrWithBLOBs.setBoxList(soldAndServices.get(0)
						.html());
				productExtraAttrWithBLOBs.setSoldServer(soldAndServices.get(1)
						.html());
				insertHistorySoldService(productExtraAttrWithBLOBs);
			}

			// 3.获取对应的图片信息
			String descurl = null; // 获取详细信息对应的url
			Elements scripts = doc.getElementsByTag("script");
			for (Element element : scripts) {
				String scriptStr = element.data();
				descurl = getProductDescUrl(scriptStr); // js中获取地址
				if (descurl != null)
					break;
			}

			if (descurl != null) {
				doc = Jsoup.connect(descurl).get();
				Elements imgs = doc.getElementsByTag("img");
				if (imgs.size() > 0) {
					List<ProductImage> list = new ArrayList<ProductImage>();
					for (Element img : imgs) {
						ProductImage productImage = new ProductImage();
						productImage.setPid(productId); // 产品ID
						String imageUrl = img.attr("src");
						productImage.setImageUrl(FileUtil.getProductsFilePath(
								productId, imageUrl.substring(imageUrl
										.lastIndexOf("/") + 1)));
						list.add(productImage);
						FileUtil.savePicToStore(productId, imageUrl);
					}
					if (list.size() > 0) {
						insertHistoryImage(list);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: insertHistoryProductFromElement
	 * @Description: 遍历不同Table 并入库
	 * @author sunlulu
	 * @param @param element
	 * @return void
	 * @throws
	 */
	public abstract void insertHistoryProductFromElement(Element element,
			String productId, String urlNum);

	/**
	 * 
	 * @Title: getProductMobile
	 * @Description: 获取商品信息(临时用)
	 * @author sunlulu
	 * @param @param suid
	 * @param @return
	 * @return ProductMobile
	 * @throws
	 */
	// public abstract ProductMobile getProductMobile(String suid);
}
