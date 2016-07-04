/**
 * 
 */
package example.proxy;

import java.lang.reflect.Proxy;

/**
 * @author sunlulu 2016年4月14日 描述：
 */
public class TestProxyMain {
	public static void main(String[] args) {

		CountInter countInter = new CountImpl();

		CountInter countInterProxy = (CountInter) Proxy.newProxyInstance(
				countInter.getClass().getClassLoader(), countInter.getClass()
						.getInterfaces(), new ProxyCount(countInter));

		countInterProxy.getSum();

		System.exit(0);

	}
}
