/**
 * 
 */
package example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author sunlulu 2016年4月14日 描述：
 */
public class ProxyList<T> implements InvocationHandler {

	private List<T> list;

	public ProxyList(List<T> list) {
		this.list = list;
	}

	/**
	 * @author sunlulu 2016年4月14日 描述：
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Object object = null;
		start();
		object = method.invoke(list, args);
		end();
		return object;
	}

	public void start() {
		System.out.println("调用list方法之前先干");
	}

	public void end() {
		System.out.println("完成list调用之后干");
	}

}
