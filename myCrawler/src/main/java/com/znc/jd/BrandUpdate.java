package com.znc.jd;

import java.util.List;

import com.znc.jd.domain.PShopBrand;
import com.znc.jd.service.impl.BrandService;
import com.znc.mycrawler.util.PinyinUtil;

/**
 * 批量修改品牌拼音，首字母
 * 
 * @ClassName: BrandUpdate
 * @Description: TODO
 * @author liyong
 * @date 2016年1月6日 下午5:30:31
 * @version v1.0
 */
public class BrandUpdate {

	public static void main(String[] args) {

		BrandService bb = new BrandService();
		List<PShopBrand> ll = bb.queryBrand();
		ll.forEach(action -> {
			String name = action.getName();
			// name = name.substring(0,name.indexOf("（"));
			name = name.replaceAll("（", "").replaceAll("）", "");
			action.setCreatedByName(PinyinUtil.cn2Spell(name));// 品牌拼音
			action.setFirstcharacter(PinyinUtil.cn2FirstSpell(name)
					.substring(0, 1).toUpperCase());// 首字母
			bb.updateBrand(action);
		});
		System.out.println("success");

	}
}
