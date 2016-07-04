/**   
 * @Title: HistoryInter.java
 * @Package com.znc.mycrawler.service
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月2日 下午6:02:37
 * @version V1.0   
 */
package com.znc.mycrawler.service;

import java.util.List;

import com.znc.mycrawler.domain.ProductExtraAttrWithBLOBs;
import com.znc.mycrawler.domain.ProductImage;
import com.znc.mycrawler.domain.ProductTitleImage;

/**
 * @ClassName: HistoryInter
 * @Description: 公共接口
 * @author sunlulu
 * @date 2015年4月2日 下午6:02:37
 */

public interface HistoryInter<T> {
	/**
	 * 
	 * @Title: insertHistoryProduct 
	 * @Description: 产品详细信息（公共接口）
	 * @author sunlulu
	 * @param @param t
	 * @return void  
	 * @throws
	 */
	public void insertHistoryProduct(T t);

	/**
	 * 
	 * @Title: insertHistorySoldService
	 * @Description: 售后和服务信息表（公共接口）
	 * @author sunlulu
	 * @param @param productExtraAttrWithBLOBs
	 * @return void
	 * @throws
	 */
	public void insertHistorySoldService(
			ProductExtraAttrWithBLOBs productExtraAttrWithBLOBs);

	/**
	 * 
	 * @Title: insertHistoryImage
	 * @Description: 产品对应的图片（公共接口，公共实现）
	 * @author sunlulu
	 * @param @param productImage
	 * @return void
	 * @throws
	 */
	public void insertHistoryImage(ProductImage productImage);

	/**
	 * 
	 * @Title: insertHistoryImage
	 * @Description: 产品对应的图片（公共接口,公共实现）
	 * @author sunlulu
	 * @param @param list
	 * @return void
	 * @throws
	 */
	public void insertHistoryImage(List<ProductImage> list);
	/**
	 * 
	 * @Title: insertHistoryTitleImage 
	 * @Description: 产品对应的滚动信息
	 * @author sunlulu
	 * @param @param list
	 * @return void  
	 * @throws
	 */
	public void insertHistoryTitleImage(List<ProductTitleImage> list);
	
	/**
	 * 
	 * @Title: haveRows
	 * @Description: 是否已经抓取过（公共接口，私有方法实现）
	 * @author sunlulu
	 * @param @param suid
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean haveRows(String suid);
	
	/**
	 * 
	 * @Title: parseMobile 
	 * @Description: TODO
	 * @author sunlulu
	 * @param @param url
	 * @return void  
	 * @throws
	 */
	public void parseProduct(String url);

}
