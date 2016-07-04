/**   
 * @Title: TestJdCategory.java
 * @Package com.znc.jd
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年12月9日 下午2:36:54
 * @version V1.0   
 */
package com.znc.jd;

import org.junit.Test;

import com.znc.jd.domain.JdCategory;
import com.znc.jd.service.impl.JdCategoryImpl;

/**
 * @ClassName: TestJdCategory
 * @Description: TODO
 * @author sunlulu
 * @date 2015年12月9日 下午2:36:54
 */

public class TestJdCategory {

	@Test
	public void test() {
		JdCategoryImpl dbAbstract = new JdCategoryImpl();

		JdCategory jdCategory = new JdCategory();
		jdCategory.setId(0L);
		jdCategory.setLevel(1);
		jdCategory.setPath("0");
		jdCategory.setUrl("http://www.baidu.com");
		jdCategory.setCategoryName("测试");
		// dbAbstract.insert(jdCategory);
	}

}
