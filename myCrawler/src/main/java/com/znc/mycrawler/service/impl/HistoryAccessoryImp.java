/**   
 * @Title: HistoryAccessoryImp.java
 * @Package com.znc.mycrawler.service.impl
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月23日 下午1:46:21
 * @version V1.0   
 */
package com.znc.mycrawler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.mycrawler.dao.ProductMobileAccessoryMapper;
import com.znc.mycrawler.dao.expand.ProductMobileAccessoryAttrMapperExt;
import com.znc.mycrawler.domain.ProductMobileAccessory;
import com.znc.mycrawler.domain.ProductMobileAccessoryAttr;
import com.znc.mycrawler.domain.ProductMobileAccessoryExample;
import com.znc.mycrawler.service.HistoryAbstract;

/**
 * @ClassName: HistoryAccessoryImp
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月23日 下午1:46:21
 */
public class HistoryAccessoryImp extends
		HistoryAbstract<ProductMobileAccessory> {

	private String classfiyCode;

	public String getClassfiyCode() {
		return classfiyCode;
	}

	public void setClassfiyCode(String classfiyCode) {
		this.classfiyCode = classfiyCode;
	}

	@Override
	public void insertHistoryProduct(
			ProductMobileAccessory productMobileAccessory) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductMobileAccessoryMapper mapper = sqlSession
				.getMapper(ProductMobileAccessoryMapper.class);
		mapper.insert(productMobileAccessory);
		sqlSession.commit();
		sqlSession.close();
	}

	private void insertHistoryProductAttr(
			List<ProductMobileAccessoryAttr> listAttr) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductMobileAccessoryAttrMapperExt mapper = sqlSession
				.getMapper(ProductMobileAccessoryAttrMapperExt.class);
		mapper.insertProductMobileAccessoryAttr(listAttr);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public boolean haveRows(String suid) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductMobileAccessoryMapper mapper = sqlSession
				.getMapper(ProductMobileAccessoryMapper.class);

		ProductMobileAccessoryExample example = new ProductMobileAccessoryExample();
		example.createCriteria().andSuningNumberEqualTo(suid);
		List<ProductMobileAccessory> list = mapper.selectByExample(example);
		sqlSession.close();
		if (list != null && list.size() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public void insertHistoryProductFromElement(Element element,
			String productId, String urlNum) {
		ProductMobileAccessory productMobileAccessory = new ProductMobileAccessory(); // 不同的产品信息
		productMobileAccessory.setId(productId);
		productMobileAccessory.setSuningNumber(urlNum); // 记录苏宁的ID
		productMobileAccessory.setClassfiyCode(getClassfiyCode()); // 不同产品设置不同的classfiycode

		// 获取商品名称
		Elements parents = element.parents();
		Element doc = parents.get(parents.size() - 1);// 获取整个doc
		Elements listProductNames = doc.select("div.title h1");
		for (Element e : listProductNames) {
			productMobileAccessory.setProductName(e.text());
		}

		List<ProductMobileAccessoryAttr> listAttr = new ArrayList<ProductMobileAccessoryAttr>();
		Elements links = element.getElementsByTag("tr");
		for (Element link : links) {
			Elements tds = link.getElementsByTag("td");
			strToObject(productMobileAccessory, tds); // 设置值
			// 设置属性值
			ProductMobileAccessoryAttr productMobileAccessoryAttr = new ProductMobileAccessoryAttr();
			productMobileAccessoryAttr.setPmaid(productId); // 对应的产品ID
			productMobileAccessoryAttr.setClassfiyCode(getClassfiyCode()); // 对应的类目code
			strToObjectAttr(productMobileAccessoryAttr, tds);
			
			listAttr.add(productMobileAccessoryAttr);
		}
		insertHistoryProduct(productMobileAccessory); // 插入数据库
		if(listAttr.size() > 0)
			insertHistoryProductAttr(listAttr);          //插入属性值
		System.out.println("xxx");
	}

	private void strToObject(ProductMobileAccessory productMobileAccessory,
			Elements tds) {
		if (tds.size() == 2) {
			String key = tds.get(0).text().trim();
			String value = tds.get(1).text().trim();
			if ("品牌".equals(key)) {
				productMobileAccessory.setBrand(value);
			}
			if ("型号".equals(key)) {
				productMobileAccessory.setPmodel(value);
			}
		}
	}

	private void strToObjectAttr(
			ProductMobileAccessoryAttr productMobileAccessoryAttr, Elements tds) {
		if (tds.size() == 2) {
			String key = tds.get(0).text().trim();
			String value = tds.get(1).text().trim();
			productMobileAccessoryAttr.setAttributeName(key);
			productMobileAccessoryAttr.setAttributeValue(value);
		}
	}

}
