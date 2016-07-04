/**   
 * @Title: HistoryPad.java
 * @Package com.znc.mycrawler.service.impl
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月3日 上午11:01:17
 * @version V1.0   
 */
package com.znc.mycrawler.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.mycrawler.dao.ProductPadMapper;
import com.znc.mycrawler.domain.ProductPad;
import com.znc.mycrawler.domain.ProductPadExample;
import com.znc.mycrawler.service.HistoryAbstract;

/**
 * 
 * @ClassName: HistoryPadImpl
 * @Description: 单个原始pad信息
 * @author sunlulu
 * @date 2015年4月20日 下午7:13:56
 */
public class HistoryPadImpl extends HistoryAbstract<ProductPad> {

	
	
	@Override
	public void insertHistoryProduct(ProductPad productPad) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductPadMapper mapper = sqlSession
				.getMapper(ProductPadMapper.class);
		mapper.insert(productPad);
		sqlSession.commit();
		sqlSession.close();
	}

	
	
	@Override
	public boolean haveRows(String suid) {
		
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductPadMapper mapper = sqlSession
				.getMapper(ProductPadMapper.class);

		ProductPadExample example = new ProductPadExample();
		example.createCriteria().andSuningNumberEqualTo(suid);
		List<ProductPad> list = mapper.selectByExample(example);
		sqlSession.close();
		if (list != null && list.size() != 0) {
			return true;
		}
		return false;
	}

	
	
	@Override
	public void insertHistoryProductFromElement(Element element,
			String productId, String urlNum) {
		ProductPad productPad = new ProductPad(); // 不同的产品信息
		productPad.setId(productId);
		productPad.setSuningNumber(urlNum); // 记录苏宁的ID

		Elements links = element.getElementsByTag("tr");
		for (Element link : links) {
			Elements tds = link.getElementsByTag("td");
			strToObject(productPad, tds); // 设置值
		}
		insertHistoryProduct(productPad); // 插入数据库
	}
	
	
	private void strToObject(ProductPad productPad, Elements tds) {
		if (tds.size() == 2) {

			String key = tds.get(0).text().trim();
			String value = tds.get(1).text().trim();

			if ("品牌".equals(key)) {
				productPad.setBrand(value);
			}
			if ("型号".equals(key)) {
				productPad.setPmodel(value);
			}
			if ("商品名称".equals(key)) {
				productPad.setProductName(value);
			}
			if ("颜色".equals(key)) {
				productPad.setColor(value);
			}
			if ("上市时间".equals(key)) {
				productPad.setMarketsoldTime(value);
			}
			if ("存储容量".equals(key)) {
				productPad.setStorageCapacity(value);
			}
			if ("操作系统".equals(key)) {
				productPad.setOs(value);
			}
			if ("处理器型号".equals(key)) {
				productPad.setCpuModel(value);
			}
			if ("核心".equals(key)) {
				productPad.setCpuCore(value);
			}
			if ("CPU主频".equals(key)) {
				productPad.setCpuFrequency(value);
			}
			if ("系统内存".equals(key)) {
				productPad.setLocalmen(value);
			}
			if ("外接扩展卡".equals(key)) {
				productPad.setMaxmen(value);
			}
			if ("屏幕尺寸".equals(key)) {
				productPad.setScreenSize(value);
			}
			if ("屏幕分辨率".equals(key)) {
				productPad.setScreenResolution(value);
			}
			if ("屏幕比例".equals(key)) {
				productPad.setScreenScale(value);
			}
			if ("屏幕类型".equals(key)) {
				productPad.setScreenModel(value);
			}
			if ("WIFI".equals(key)) {
				productPad.setWifi(value);
			}
			
			if ("蓝牙功能".equals(key)) {
				productPad.setBuletooth(value);
			}
			if ("音频端口".equals(key)) {
				productPad.setAudioPort(value);
			}
			if ("USB端口".equals(key)) {
				productPad.setUsbPort(value);
			}
			if ("扬声器".equals(key)) {
				productPad.setSpeakers(value);
			}
			if ("麦克风".equals(key)) {
				productPad.setMicrophone(value);
			}
			if ("摄像头".equals(key)) {
				productPad.setVideo(value);
			}
			if ("Flash播放".equals(key)) {
				productPad.setFlash(value);
			}
			if ("光线感应".equals(key)) {
				productPad.setLight(value);
			}
			if ("重力感应".equals(key)) {
				productPad.setGravity(value);
			}
			if ("GPS导航".equals(key)) {
				productPad.setGps(value);
			}
			if ("电池类型".equals(key)) {
				productPad.setBatteryModel(value);
			}
			if ("电池容量".equals(key)) {
				productPad.setBatteryCapacity(value);
			}
			if ("续航时间".equals(key)) {
				productPad.setStandbyTime(value);
			}
			if ("机身尺寸".equals(key)) {
				productPad.setOutSize(value);
			}
			if ("重量".equals(key)) {
				productPad.setWeight(value);
			}
			if ("厂商保修政策".equals(key)) {
				productPad.setWarrantyPolicy(value);
			}
			if ("通话功能".equals(key)) {
				productPad.setCallFunction(value);
			}
			if ("3G类型".equals(key)) {
				productPad.setThreeg(value);
			}
			if ("多点触摸".equals(key)) {
				productPad.setMultipoint(value);
			}
			if ("视频端口".equals(key)) {
				productPad.setVideoPort(value);
			}
		}
	}
}
