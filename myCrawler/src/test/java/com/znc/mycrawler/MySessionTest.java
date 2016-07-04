/**   
 * @Title: MySessionTest.java
 * @Package com.znc.mycrawler
 * @Description: TODO
 * @author sunlulu   
 * @date 2015年4月3日 上午10:19:23
 * @version V1.0   
 */
package com.znc.mycrawler;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import com.znc.mycrawler.util.FileUtil;
import com.znc.mycrawler.util.MySessionFactory;

/**
 * @ClassName: MySessionTest
 * @Description: TODO
 * @author sunlulu
 * @date 2015年4月3日 上午10:19:23
 */

public class MySessionTest {
    @Ignore
	@Test
	public void test() {
		while (true) {
			SqlSession sqlSession = MySessionFactory.mySessionFactory
					.getFactory().openSession();
			sqlSession.close();
		}

	}
    
    @Ignore
    @Test
    public void image() throws MalformedURLException, IOException{
//    	String imageUrl = "http://image.suning.cn/uimg/sop/commodity/352371186514256932940700_xx.jpg";
//    	
//    	FileUtil.savePicToStore("test123", imageUrl);
//    	FileUtil.savePicToStore("test123", imageUrl);
    	
    	String url = "/list/179001-0.html";
    	url = url.substring(url.lastIndexOf("/")+1, url.indexOf("."));
    	System.out.println(url);
    }
    
    
    @Test
    public void testT(){
    	
    	int a = 1;
    	System.out.println(a);
    	
    }

}
