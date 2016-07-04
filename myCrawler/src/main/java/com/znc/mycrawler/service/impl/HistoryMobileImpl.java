/**   
 * @Title: HistoryMobileImpl.java
 * @Package com.znc.mycrawler.service.impl
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月1日 下午6:28:50
 * @version V1.0   
 */
package com.znc.mycrawler.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.znc.mycrawler.dao.ProductMobileMapper;
import com.znc.mycrawler.domain.ProductMobile;
import com.znc.mycrawler.domain.ProductMobileExample;
import com.znc.mycrawler.service.HistoryAbstract;

/**
 * @ClassName: HistoryMobileImpl
 * @Description: service层原始手机插入
 * @author sunlulu
 * @date 2015年4月1日 下午6:28:50
 */

public class HistoryMobileImpl extends HistoryAbstract<ProductMobile> {

	private static Logger log = Logger.getLogger(HistoryMobileImpl.class);

	@Override
	public void insertHistoryProduct(ProductMobile productMobile) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductMobileMapper mapper = sqlSession
				.getMapper(ProductMobileMapper.class);
		mapper.insert(productMobile);
		sqlSession.commit();
		sqlSession.close();
	}

	// @Override
	// public void insertHistorySoldService(
	// ProductExtraAttrWithBLOBs productExtraAttrWithBLOBs) {
	// SqlSession sqlSession = this.getSqlSessionFactory().openSession();
	// ProductExtraAttrMapper mapper = sqlSession
	// .getMapper(ProductExtraAttrMapper.class);
	// mapper.insert(productExtraAttrWithBLOBs);
	// sqlSession.commit();
	// sqlSession.close();
	//
	// }
	//
	// @Override
	// public void insertHistoryImage(ProductImage productImage) {
	// SqlSession sqlSession = this.getSqlSessionFactory().openSession();
	// ProductImageMapper mapper = sqlSession
	// .getMapper(ProductImageMapper.class);
	// mapper.insert(productImage);
	// sqlSession.commit();
	// sqlSession.close();
	// }
	//
	// @Override
	// public void insertHistoryImage(List<ProductImage> list) {
	// SqlSession sqlSession = this.getSqlSessionFactory().openSession();
	// ProductImageMapperExt mapper = sqlSession
	// .getMapper(ProductImageMapperExt.class);
	// mapper.insertProductImageList(list);
	// sqlSession.commit();
	// sqlSession.close();
	// }

	@Override
	public boolean haveRows(String suid) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductMobileMapper mapper = sqlSession
				.getMapper(ProductMobileMapper.class);

		ProductMobileExample example = new ProductMobileExample();
		example.createCriteria().andSuningNumberEqualTo(suid);
		List<ProductMobile> list = mapper.selectByExample(example);
		sqlSession.close();
		if (list != null && list.size() != 0) {
			return true;
		}
		return false;
	}
	
	public ProductMobile getProductMobile(String suid) {
		SqlSession sqlSession = this.getSqlSessionFactory().openSession();
		ProductMobileMapper mapper = sqlSession
				.getMapper(ProductMobileMapper.class);

		ProductMobileExample example = new ProductMobileExample();
		example.createCriteria().andSuningNumberEqualTo(suid);
		List<ProductMobile> list = mapper.selectByExample(example);
		sqlSession.close();
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	public void insertHistoryProductFromElement(Element element,
			String productId, String urlNum) {

		ProductMobile productMobile = new ProductMobile(); // 不同的产品信息
		productMobile.setId(productId);
		productMobile.setSuningNumber(urlNum); // 记录苏宁的ID

		Elements links = element.getElementsByTag("tr");
		for (Element link : links) {
			Elements tds = link.getElementsByTag("td");
			strToObject(productMobile, tds); // 设置值
		}
		insertHistoryProduct(productMobile); // 插入数据库
	}

	/**
	 * 
	 * @Title: parseMobile
	 * @Description:
	 * @author sunlulu
	 * @param @param url
	 * @return void
	 * @throws
	 */
	// public void parseMobile(String url) {
	//
	// String urlNum = url.substring(url.lastIndexOf("/") + 1,
	// url.lastIndexOf("."));
	// if (haveRows(urlNum))
	// return;// 如果已经抓取返回
	//
	// Document doc;
	// try {
	// doc = Jsoup.connect(url).get();
	//
	// // 1.获取原始数据信息
	// Elements ListDiv = doc.getElementsByAttributeValue("class",
	// "propList");
	//
	// String productId = CommonUtil.getId();// 不重复的32位字符串
	//
	// for (Element element : ListDiv) {
	// // 封装对象
	// ProductMobile productMobile = new ProductMobile();
	// productMobile.setId(productId);
	// productMobile.setSuningNumber(urlNum); // 记录苏宁的ID
	//
	// Elements links = element.getElementsByTag("tr");
	// for (Element link : links) {
	// Elements tds = link.getElementsByTag("td");
	// strToObject(productMobile, tds); // 设置值
	// }
	// insertHistoryProduct(productMobile); // 插入数据库
	// }
	// // 2.获取包装和服务信息
	// Elements soldAndServices = doc
	// .select("section:gt(0) div.sectionContent");
	// if (soldAndServices.size() > 0) {
	// ProductExtraAttrWithBLOBs productExtraAttrWithBLOBs = new
	// ProductExtraAttrWithBLOBs();
	// productExtraAttrWithBLOBs.setPid(productId);
	// productExtraAttrWithBLOBs.setBoxList(soldAndServices.get(0)
	// .html());
	// productExtraAttrWithBLOBs.setSoldServer(soldAndServices.get(1)
	// .html());
	// insertHistorySoldService(productExtraAttrWithBLOBs);
	// }
	//
	// // 3.获取对应的图片信息
	// String descurl = null; // 获取详细信息对应的url
	// Elements scripts = doc.getElementsByTag("script");
	// for (Element element : scripts) {
	// String scriptStr = element.data();
	// descurl = getProductDescUrl(scriptStr); // js中获取地址
	// if (descurl != null)
	// break;
	// }
	//
	// if (descurl != null) {
	// doc = Jsoup.connect(descurl).get();
	// Elements imgs = doc.getElementsByTag("img");
	// if (imgs.size() > 0) {
	// List<ProductImage> list = new ArrayList<ProductImage>();
	// for (Element img : imgs) {
	// ProductImage productImage = new ProductImage();
	// productImage.setPid(productId); // 产品ID
	// String imageUrl = img.attr("src");
	// productImage.setImageUrl(FileUtil.getProductsFilePath(
	// productId, imageUrl.substring(imageUrl
	// .lastIndexOf("/") + 1)));
	// list.add(productImage);
	// FileUtil.savePicToStore(productId, imageUrl);
	// }
	// if (list.size() > 0) {
	// insertHistoryImage(list);
	// }
	// }
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	private void strToObject(ProductMobile productMobile, Elements tds) {
		if (tds.size() == 2) {

			String key = tds.get(0).text().trim();
			String value = tds.get(1).text().trim();

			if ("品牌".equals(key)) {
				productMobile.setBrand(value);
			}
			if ("型号".equals(key)) {
				productMobile.setPmodel(value);
			}
			if ("上市时间".equals(key)) {
				productMobile.setMarketsoldTime(value);
			}
			if ("外观样式".equals(key)) {
				productMobile.setOutward(value);
			}
			if ("颜色".equals(key)) {
				productMobile.setColor(value);
			}
			if ("智能机".equals(key)) {
				productMobile.setIntelligent(value);
			}
			if ("系统版本".equals(key)) {
				productMobile.setSystemversion(value);
			}
			if ("CPU型号".equals(key)) {
				productMobile.setCpuModel(value);
			}
			if ("CPU频率".equals(key)) {
				productMobile.setCpuFrequency(value);
			}
			if ("CPU核数".equals(key)) {
				productMobile.setCpuCore(value);
			}
			if ("手机操控方式".equals(key)) {
				productMobile.setMobileOper(value);
			}
			if ("手机操作系统".equals(key)) {
				productMobile.setMobileOs(value);
			}
			if ("待机模式".equals(key)) {
				productMobile.setWaitModel(value);
			}
			if ("运营商标识".equals(key)) {
				productMobile.setOperatorMark(value);
			}
			if ("SIM卡尺寸".equals(key)) {
				productMobile.setSim(value);
			}
			if ("视频通话".equals(key)) {
				productMobile.setVideo(value);
			}
			if ("4G网络制式".equals(key)) {
				productMobile.setFourg(value);
			}
			if ("3G网络制式".equals(key)) {
				productMobile.setThreeg(value);
			}
			if ("2G网络制式".equals(key)) {
				productMobile.setTwog(value);
			}
			if ("手机制式".equals(key)) {
				productMobile.setMobileModel(value);
			}
			if ("网络频率".equals(key)) {
				productMobile.setNetwork(value);
			}
			if ("机身内存".equals(key)) {
				productMobile.setLocalmen(value);
			}
			if ("运行内存".equals(key)) {
				productMobile.setRunmen(value);
			}
			if ("存储卡类型".equals(key)) {
				productMobile.setStoreModel(value);
			}
			if ("最大存储扩展".equals(key)) {
				productMobile.setMaxmen(value);
			}
			if ("屏幕材质".equals(key)) {
				productMobile.setScreenTexture(value);
			}
			if ("屏幕尺寸".equals(key)) {
				productMobile.setScreenSize(value);
			}
			if ("屏幕分辨率".equals(key)) {
				productMobile.setScreenResolution(value);
			}
			if ("屏幕颜色".equals(key)) {
				productMobile.setScreenColor(value);
			}
			if ("重力传感器".equals(key)) {
				productMobile.setGravity(value);
			}
			if ("光线传感器".equals(key)) {
				productMobile.setLight(value);
			}
			if ("距离传感器".equals(key)) {
				productMobile.setDistance(value);
			}
			if ("陀螺仪".equals(key)) {
				productMobile.setGyroscope(value);
			}
			if ("指南针".equals(key)) {
				productMobile.setCompass(value);
			}
			if ("超大字体".equals(key)) {
				productMobile.setSuperfont(value);
			}
			if ("摄像头像素".equals(key)) {
				productMobile.setVideoPixel(value);
			}
			if ("副摄像头像素".equals(key)) {
				productMobile.setSubvideoPixel(value);
			}
			if ("闪光灯类型".equals(key)) {
				productMobile.setFlashlight(value);
			}
			if ("传感器类型".equals(key)) {
				productMobile.setSensorModel(value);
			}
			if ("变焦模式".equals(key)) {
				productMobile.setFocalModel(value);
			}
			if ("自动对焦".equals(key)) {
				productMobile.setAutoFocal(value);
			}
			if ("连拍功能".equals(key)) {
				productMobile.setTakePicture(value);
			}
			if ("蓝牙版本".equals(key)) {
				productMobile.setBuletooth(value);
			}
			if ("标配电池容量".equals(key)) {
				productMobile.setBattery(value);
			}
			if ("电池更换".equals(key)) {
				productMobile.setChangeBattery(value);
			}
			if ("个人助理".equals(key)) {
				productMobile.setPersonHelp(value);
			}
			if ("外形尺寸".equals(key)) {
				productMobile.setOutSize(value);
			}
			if ("重量".equals(key)) {
				productMobile.setWeight(value);
			}
			if ("耳机接口".equals(key)) {
				productMobile.setEraphone(value);
			}
			if ("数据线".equals(key)) {
				productMobile.setDataLight(value);
			}
			if ("数据业务".equals(key)) {
				productMobile.setDataBusiness(value);
			}
			if ("CCC认证字".equals(key)) {
				productMobile.setCcc(value);
			}
			if ("PC数据同步".equals(key)) {
				productMobile.setPcSyc(value);
			}
			if ("3D加速".equals(key)) {
				productMobile.setThreed(value);
			}
			if ("NFC传输".equals(key)) {
				productMobile.setNfcTrans(value);
			}
			if ("Wi-Fi".equals(key)) {
				productMobile.setWifi(value);
			}
			if ("个人热点".equals(key)) {
				productMobile.setPersonHot(value);
			}
			if ("GPS模块（硬件）".equals(key)) {
				productMobile.setGps(value);
			}
			if ("理论待机时间".equals(key)) {
				productMobile.setStandbyTime(value);
			}
			if ("理论通话时间".equals(key)) {
				productMobile.setTalkTime(value);
			}
		}
	}

}
