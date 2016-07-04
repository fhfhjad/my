/**   
 * @Title: JdCategoryImpl.java
 * @Package com.znc.jd.service.impl
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年12月9日 下午2:28:46
 * @version V1.0   
 */
package com.znc.jd.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.znc.jd.dao.JdCategoryMapper;
import com.znc.jd.dao.JdGoodsAttrvalMapper;
import com.znc.jd.dao.JdGoodsImageMapper;
import com.znc.jd.dao.JdGoodsMapper;
import com.znc.jd.domain.JdCategory;
import com.znc.jd.domain.JdCategoryExample;
import com.znc.jd.domain.JdColorSize;
import com.znc.jd.domain.JdGoods;
import com.znc.jd.domain.JdGoodsAttrval;
import com.znc.jd.domain.JdGoodsImage;
import com.znc.jd.domain.JdGoodsImageExample;
import com.znc.jd.service.DbAbstract;
import com.znc.mycrawler.util.CommonUtil;
import com.znc.mycrawler.util.FileUtil;

/**
 * @ClassName: JdCategoryImpl
 * @Description: TODO
 * @author sunlulu
 * @date 2015年12月9日 下午2:28:46
 */

public class JdCategoryImpl extends DbAbstract {

	public static Logger logger = Logger.getLogger(JdCategoryImpl.class);
	
	/**
	 * 插入数据到jd_category
	 * @param jdCategory
	 */
	public void insertJdCategory(JdCategory jdCategory) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdCategoryMapper mapper = sqlSession.getMapper(JdCategoryMapper.class);
		mapper.insert(jdCategory);
		closeSqlSession(sqlSession);
	}

	/**
	 * 更新jd_category的数据
	 * @param jdCategory
	 */
	public void updateJdCategory(JdCategory jdCategory){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdCategoryMapper mapper = sqlSession.getMapper(JdCategoryMapper.class);
		mapper.updateByPrimaryKey(jdCategory);
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 删除jd_category数据
	 * @param jdCategory
	 */
	public void deleteJdCategory(JdCategory jdCategory) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdCategoryMapper mapper = sqlSession.getMapper(JdCategoryMapper.class);
		mapper.deleteByPrimaryKey(jdCategory.getId());
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 根据名字获取分类数据
	 * @param categoryName
	 * @return
	 */
	public JdCategory getJdCategory(String categoryName){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdCategoryMapper mapper = sqlSession.getMapper(JdCategoryMapper.class);
		
		JdCategoryExample example = new JdCategoryExample();
		example.createCriteria().andCategoryNameEqualTo(categoryName);
		List<JdCategory> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据名字获取商品图片信息
	 * @param imgName
	 * @return
	 */
	public JdGoodsImage getJdGoodsImage(String imgName){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsImageMapper mapper = sqlSession.getMapper(JdGoodsImageMapper.class);
		
		JdGoodsImageExample example = new JdGoodsImageExample();
		example.createCriteria().andImgNameEqualTo(imgName);
		List<JdGoodsImage> list = mapper.selectByExample(example);
		closeSqlSession(sqlSession);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	
	/**
	 * 根据主键获取JdCategory信息
	 * @param id
	 * @return
	 */
	public JdCategory queryJdCategoryById(Long id){
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdCategoryMapper mapper = sqlSession.getMapper(JdCategoryMapper.class);
		JdCategory jdCategory = mapper.selectByPrimaryKey(id);
		closeSqlSession(sqlSession);
		if(jdCategory != null)
			return jdCategory;
		return null;
	}
	
	/**
	 * 插入数据到jd_goods
	 * @param jdGoods
	 */
	public void insertJdGoods(JdGoods jdGoods) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsMapper mapper = sqlSession.getMapper(JdGoodsMapper.class);
		mapper.insert(jdGoods);
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean queryJdGoodsByGoodsId(Long goodsId){
		
		boolean result = true;
		
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsMapper mapper = sqlSession.getMapper(JdGoodsMapper.class);
		JdGoods jdGoods = mapper.selectByPrimaryKey(goodsId);
		closeSqlSession(sqlSession);
		if(jdGoods == null){
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 插入数据到jd_goods_attrval
	 * @param jdGoodsAttrval
	 */
	public void insertJdGoodsAttrval(JdGoodsAttrval jdGoodsAttrval) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsAttrvalMapper mapper = sqlSession.getMapper(JdGoodsAttrvalMapper.class);
		mapper.insert(jdGoodsAttrval);
		closeSqlSession(sqlSession);
	}
	
	/**
	 * 插入数据到jd_goods_image
	 * @param jdGoodsImage
	 */
	public void insertJdGoodsImage(JdGoodsImage jdGoodsImage) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		JdGoodsImageMapper mapper = sqlSession.getMapper(JdGoodsImageMapper.class);
		mapper.insert(jdGoodsImage);
		closeSqlSession(sqlSession);
	}
	
	
	
	/**
	 * 解析JD分类html文件，将数据插入到京东分类表jd_category中
	 */
	public void parseJdCategoryHtml(){
		File jdHtml = new File("jd/jd.html");
		Document doc;
		try {
			doc = Jsoup.parse(jdHtml, "GBK");
			
			Elements ListDiv = doc.getElementsByAttributeValue("class",
					"subitems");
			
			for (Element element : ListDiv) {
				//获得整个二级分类列表（包括二级分类下的三级分类）
				Elements linksDL = element.getElementsByTag("dl");
				for(Element dl : linksDL){
					
					JdCategory category = new JdCategory();
					
					//获得二级分类列表
					Elements linksDT = dl.getElementsByTag("dt");
					for(Element dt : linksDT){
						Elements linksA = dt.getElementsByTag("a");
						for(Element a : linksA){
							String linkText = a.text().trim().replace(">", "");
							JdCategory jdCategory = new JdCategory();
							jdCategory.setParentId(0L);
							jdCategory.setCategoryName(linkText);
							jdCategory.setLevel(2);
							
							//加入判断是否已存在同名CategoryName数据，存在则跳出循环
							if(null != this.getJdCategory(linkText)){
								continue;
							}else{
								this.insertJdCategory(jdCategory);
								Long id = jdCategory.getId();
								
								jdCategory.setPath("0|" + id);
								this.updateJdCategory(jdCategory);
								
								category.setParentId(id);
								category.setPath(jdCategory.getPath());
							}
						}
					}
					//获得二级分类列表下的三级分类
					Elements linkDD = dl.getElementsByTag("dd");
					for(Element dd : linkDD){
						Elements linksA = dd.getElementsByTag("a");
						for(Element a : linksA){
							String linkText = a.text().trim();
							String linkUrl = a.attr("href");
							
							if(linkUrl.contains("cat=")){
								JdCategory jdCategory = new JdCategory();
								
								jdCategory.setParentId(category.getParentId());
								jdCategory.setCategoryName(linkText);
								jdCategory.setUrl(linkUrl);
								jdCategory.setLevel(3);
								
								//加入判断是否已存在同名CategoryName数据，存在则跳出循环
								if(null != this.getJdCategory(linkText)){
									continue;
								}else{
									this.insertJdCategory(jdCategory);
									Long id = jdCategory.getId();
									jdCategory.setPath(category.getPath() + "|" + id);
									this.updateJdCategory(jdCategory);
								}
							}
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析每个分类下的商品详细信息
	 * @param jdCategory 京东商品分类对象
	 */
	public void parseEveryJdCategoryDetail(JdCategory jdCategory){
		Document doc;
		
		//统计采集商品数
		int goodsCount = 0;
		logger.info("ZnkecategoryName--->>" + jdCategory.getZnkeCategoryName());
//		String rootUrl = "http://list.jd.com/list.html?cat=9987,653,655";
		String rootUrl = jdCategory.getUrl() + "&delivery=1&stock=0";
		
		try {
			doc = Jsoup.connect(rootUrl).get();
			
			int pageCount = 0;
			//取出来每个三级分类所包括商品的总页数
			Elements list = doc.select(".p-skip");
			for(Element element : list){
				Elements listB = element.getElementsByTag("b");
				
				for(Element b : listB){
					pageCount = Integer.parseInt(b.text().trim());
					if(pageCount > 0)
						break;
				}
			}
			logger.info("pageCount------------" + pageCount + "-------------");
			//取每页数据
			for (int i = 1; i <= pageCount; i++) {
				
				long startTime = System.currentTimeMillis();
				
				//取京东自营+全部货物
				String pageUrl = rootUrl + "&page=" + i + "&JL=6_0_0";
				logger.info("pageUrl--------------" + pageUrl + "------------");
				Document docPage;
				try {
					docPage = Jsoup.connect(pageUrl).timeout(5000).get();
					//获得所有class=p-img的元素
					Elements listPImg = docPage.select(".p-img");
					
					for (Element pimg : listPImg) {
						Elements listHref = pimg.getElementsByTag("a");
						for (Element href : listHref) {
							//京东商品信息
							JdGoods jdGoods = new JdGoods();
							
							String linkUrl = href.attr("href");
//						System.out.println(linkUrl);
							//定位到每个京东商品的详情页，获取相应的商品信息
							Document goodsDetailpage;
							try {
								goodsDetailpage = Jsoup.connect(linkUrl).timeout(5000).get();
								//定位到详情页的script，获取同一系列的商品数据
								Element script = goodsDetailpage.select("script").first();
								String scriptHtml = script.html();
								
								Pattern pattern = Pattern.compile("colorSize: .*?]");
								Matcher matcher = pattern.matcher(scriptHtml);
								
								Pattern patternO = Pattern.compile("colorSize: \\{\\}");
								Matcher matcherO = patternO.matcher(scriptHtml);
								
								if(matcherO.find()){
									String colorSizeNull = matcherO.group();
									
									if("colorSize: {}".equals(colorSizeNull) ){
										String uuid = CommonUtil.getId();
										//定位到商品介绍
										Elements listDetail = goodsDetailpage.select(".p-parameter-list");
										for (Element detail : listDetail) {
											Elements detailLi = detail.getElementsByTag("li");
											for(Element li : detailLi){
												String content = li.text();
												if(content.contains("商品名称")){
													jdGoods.setGoodsName(li.attr("title"));
												}else if(content.contains("商品编号")){
													jdGoods.setGoodsId(Long.parseLong(li.attr("title")));
												}else if(content.contains("品牌")){
													jdGoods.setGoodsBrand(li.attr("title"));
												}
											}
										}
										//根据商品ID判断是否重复
										if(!this.queryJdGoodsByGoodsId(jdGoods.getGoodsId())){
											//商品标题
											String goodsTitle = goodsDetailpage.select("div#name").first().text();
											jdGoods.setGoodsTitle(goodsTitle);
											
											//商品包装清单
											Elements itemDetail = goodsDetailpage.select(".item-detail");
											jdGoods.setGoodsBox(itemDetail.first().text());
											
											jdGoods.setCategoryId(jdCategory.getId());
											jdGoods.setCategoryPath(jdCategory.getPath());
											jdGoods.setStatus(1);
											jdGoods.setGoodsSeriel(uuid);
											
											//插入数据到京东商品信息表（jd_goods）
											this.insertJdGoods(jdGoods);
											goodsCount++;
											
											//商品图片
											Elements imgList = goodsDetailpage.select("div.spec-items");
											for (Element img : imgList) {
												
												Elements imgDetailList = img.getElementsByTag("img");
												for(Element eleImg : imgDetailList){
													String imgDetail = eleImg.attr("src");
													
													//保存图片到图片服务器
													imgDetail = imgDetail.replace("/n5/", "/n5%20/");
													FileUtil.savePicToStore(jdGoods.getGoodsId().toString(), "http:" + imgDetail);
													
													//插入数据到京东商品图片表（jd_goods_image）
													JdGoodsImage jdGoodsImage = new JdGoodsImage();
													jdGoodsImage.setGoodsId(jdGoods.getGoodsId());
													
													String imageName = imgDetail.substring(imgDetail.lastIndexOf("/") + 1);
													String dest = FileUtil.getProductsFilePath(jdGoods.getGoodsId().toString(), imageName);
													
													jdGoodsImage.setGoodsUrl(dest);
													jdGoodsImage.setImgName(imageName);
													
													this.insertJdGoodsImage(jdGoodsImage);
												}
												
												
											}
											
											//商品属性值
											Elements tableinfo = goodsDetailpage.select(".Ptable");
											for (Element table : tableinfo) {
												Elements trList = table.getElementsByTag("tr");
												for (Element tr : trList) {
													if(!"".equals(tr.text().trim())){
														Elements th = tr.getElementsByTag("th");
														JdGoodsAttrval jdGoodsAttrval = new JdGoodsAttrval();
														jdGoodsAttrval.setGoodsId(jdGoods.getGoodsId());
														
														if (!"".equals(th.text().trim())){
															jdGoodsAttrval.setGoodsAttr(th.text());
														} else {
															Element tdFirst = tr.getElementsByTag("td").first();
															Element tdLast = tr.getElementsByTag("td").last();
															
															jdGoodsAttrval.setGoodsAttr(tdFirst.text());
															jdGoodsAttrval.setGoodsVal(tdLast.text());
														}
														
														this.insertJdGoodsAttrval(jdGoodsAttrval);
													}
												}
											}
										}
									}
								}else if (matcher.find()) {
									String colorSize = matcher.group();
									
									colorSize = colorSize.replace("colorSize: ", "");
									
									Gson gson = new Gson();
									JsonParser parser = new JsonParser();
									JsonElement el = parser.parse(colorSize);
									
									JsonArray jsonArray = null;
									if(el.isJsonArray()){
										jsonArray = el.getAsJsonArray();
									}
									
									JdColorSize jdColorSize = null;
									Iterator<JsonElement> it = jsonArray.iterator();
									
									String uuid = CommonUtil.getId();
//								String tempColor = "";
									while(it.hasNext()){
										JsonElement e = it.next();
										jdColorSize = gson.fromJson(e, JdColorSize.class);
										
//									boolean flag = false;
										//获得京东商品ID
										String jdProductId = jdColorSize.getSkuId();
										
//									if(!"".equals(tempColor) && tempColor.equals(jdColorSize.getColor())){
//										//代表是同一种颜色或其他标识，下面下载图片时不再重复下载
//										flag = true;
//									}
//									
//									tempColor = jdColorSize.getColor();
										//根据商品ID判断是否重复
										if(!this.queryJdGoodsByGoodsId(Long.parseLong(jdProductId))){
											
											String detailUrl = linkUrl.replace(linkUrl.substring(linkUrl.lastIndexOf("/") + 1, linkUrl.lastIndexOf(".")), jdProductId);
											Document docDetail;
											try {
												docDetail = Jsoup.connect(detailUrl).timeout(5000).get();
												//定位到商品介绍
												Elements listDetail = docDetail.select(".p-parameter-list");
												for (Element detail : listDetail) {
													Elements detailLi = detail.getElementsByTag("li");
													for(Element li : detailLi){
														String content = li.text();
														if(content.contains("商品名称")){
															jdGoods.setGoodsName(li.attr("title"));
														}else if(content.contains("商品编号")){
															jdGoods.setGoodsId(Long.parseLong(li.attr("title")));
														}else if(content.contains("品牌")){
															jdGoods.setGoodsBrand(li.attr("title"));
														}
													}
												}
												
												//商品标题
												String goodsTitle = docDetail.select("div#name").first().text();
												jdGoods.setGoodsTitle(goodsTitle);
												
												//商品包装清单
												Elements itemDetail = docDetail.select(".item-detail");
												jdGoods.setGoodsBox(itemDetail.first().text());
												
												jdGoods.setCategoryId(jdCategory.getId());
												jdGoods.setCategoryPath(jdCategory.getPath());
												jdGoods.setStatus(1);
												jdGoods.setGoodsSeriel(uuid);
												
												//插入数据到京东商品信息表（jd_goods）
												this.insertJdGoods(jdGoods);
												goodsCount++;
												
												//商品图片
												Elements imgList = docDetail.select("div.spec-items");
//											String tempDest = "";
												for (Element img : imgList) {
													
													Elements imgDetailList = img.getElementsByTag("img");
													for(Element eleImg : imgDetailList){
														String imgDetail = eleImg.attr("src");
														
														//插入数据到京东商品图片表（jd_goods_image）
														JdGoodsImage jdGoodsImage = new JdGoodsImage();
														jdGoodsImage.setGoodsId(jdGoods.getGoodsId());
														
														String imageName = imgDetail.substring(imgDetail.lastIndexOf("/") + 1);
														String dest = FileUtil.getProductsFilePath(jdGoods.getGoodsId().toString(), imageName);
														
														JdGoodsImage jdImage = this.getJdGoodsImage(imageName);
														
														//为空则下载图片
														if(null == jdImage){
															//保存图片到图片服务器
															String imgSrc = eleImg.attr("src");
															imgSrc = imgSrc.replace("/n5/", "/n5%20/");
															FileUtil.savePicToStore(jdGoods.getGoodsId().toString(), "http:" + imgSrc);
															jdGoodsImage.setGoodsUrl(dest);
														}else{
															jdGoodsImage.setGoodsUrl(jdImage.getGoodsUrl());
														}
														
														jdGoodsImage.setImgName(imageName);
														
														this.insertJdGoodsImage(jdGoodsImage);
													}
													
													
												}
												
												//商品属性值
												Elements tableinfo = docDetail.select(".Ptable");
												for (Element table : tableinfo) {
													Elements trList = table.getElementsByTag("tr");
													for (Element tr : trList) {
														if(!"".equals(tr.text().trim())){
															Elements th = tr.getElementsByTag("th");
															JdGoodsAttrval jdGoodsAttrval = new JdGoodsAttrval();
															jdGoodsAttrval.setGoodsId(jdGoods.getGoodsId());
															
															if (!"".equals(th.text().trim())){
																jdGoodsAttrval.setGoodsAttr(th.text());
															} else {
																Element tdFirst = tr.getElementsByTag("td").first();
																Element tdLast = tr.getElementsByTag("td").last();
																
																jdGoodsAttrval.setGoodsAttr(tdFirst.text());
																jdGoodsAttrval.setGoodsVal(tdLast.text());
															}
															
															//插入数据到京东商品属性值表（jd_goods_attrval）
															this.insertJdGoodsAttrval(jdGoodsAttrval);
														}
													}
												}
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										}
									}
								} 
							} catch (IOException ioex) {
								// TODO Auto-generated catch block
								ioex.printStackTrace();
							}
							//调试用，只执行一次
//						break;
						}
						//调试用，只执行一次
//					break;
					}
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				//调试用，只执行一次
//				break;
				long endTime = System.currentTimeMillis();
				logger.info("Time to one page:" + (endTime-startTime) + ">>>get data:" + goodsCount);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
