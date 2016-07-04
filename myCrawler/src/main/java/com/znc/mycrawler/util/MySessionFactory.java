/**   
 * @Title: MySessionFactory.java
 * @Package com.znc.mycrawler.util
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月3日 上午10:13:47
 * @version V1.0   
 */
package com.znc.mycrawler.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @ClassName: MySessionFactory
 * @Description: 单例枚举SqlSessionFactory
 * @author sunlulu
 * @date 2015年4月3日 上午10:13:47
 */

public enum MySessionFactory {

	mySessionFactory;
	private SqlSessionFactory factory;

	private MySessionFactory() {
		try {
			singletonOperation();
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	public void singletonOperation() throws IOException {
		String resource = "mybatisConfig.xml";
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SqlSessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}
}
