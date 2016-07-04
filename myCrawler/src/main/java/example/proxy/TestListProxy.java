/**
 * 
 */
package example.proxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunlulu 2016年4月14日 描述：
 */
public class TestListProxy {

	/**
	 * @author sunlulu 2016年4月14日 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> list = new ArrayList<String>();

		Class<?>[] cls = list.getClass().getInterfaces();

		List<String> listProxy = (List<String>) Proxy.newProxyInstance(
				List.class.getClassLoader(), cls, new ProxyList<String>(list));

		listProxy.add("1");

		// System.out.println(list.size());
		System.out.println(list.size());

	}
}
