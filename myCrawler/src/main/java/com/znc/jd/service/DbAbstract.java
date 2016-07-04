/**   
 * @Title: DbAbstract.java
 * @Package com.znc.jd.service
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年12月9日 下午2:21:57
 * @version V1.0   
 */
package com.znc.jd.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.znc.mycrawler.util.MySessionFactory;

/**
 * @ClassName: DbAbstract
 * @Description: TODO
 * @author sunlulu
 * @date 2015年12月9日 下午2:21:57
 */

public abstract class DbAbstract {

	public static Logger logger = Logger.getLogger(DbAbstract.class);

	public SqlSessionFactory getSqlSessionFactory() {
		return MySessionFactory.mySessionFactory.getFactory();
	}

	public void closeSqlSession(SqlSession sqlSession) {
		sqlSession.commit();
		sqlSession.close();
	}

}
