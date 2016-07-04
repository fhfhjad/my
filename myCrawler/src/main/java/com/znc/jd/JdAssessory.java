package com.znc.jd;

import org.apache.log4j.Logger;

import com.znc.jd.domain.JdCategory;
import com.znc.jd.service.impl.JdCategoryImpl;
import com.znc.jd.service.impl.JdProductToZnkeImpl;

public class JdAssessory {

	public static Logger logger = Logger.getLogger(JdAssessory.class);
	
	public static void main(String[] args) {
		JdCategoryImpl jdImpl = new JdCategoryImpl();

		Long[] ids = new Long[]{1L,2L,3L,4L,5L,6L,7L,8L,9L,10L};
//		Long[] ids = new Long[]{2L};
		for (int i = 0; i < ids.length; i++) {
			JdCategory jdCategory = jdImpl.queryJdCategoryById(ids[i]);
			if(null != jdCategory){
				logger.info("----------start parse JdGoods----------");
				jdImpl.parseEveryJdCategoryDetail(jdCategory);
			}
		}
		logger.info("----------end parse JdGoods----------");
		//JD-->>>ZNKE
		JdProductToZnkeImpl productImpl = new JdProductToZnkeImpl();
		productImpl.moveJdDataToZnke();
		
	}

}
