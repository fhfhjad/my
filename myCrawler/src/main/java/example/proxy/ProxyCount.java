/**
 * 
 */
package example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author sunlulu 2016年4月14日 描述：
 */
public class ProxyCount implements InvocationHandler {

	private CountInter countInter;

	public ProxyCount(CountInter countInter) {
		this.countInter = countInter;
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
		// TODO Auto-generated method stub
		// start
		start();
		Object object = method.invoke(countInter, args);
		end();
		return object;
	}

	public void start() {
		System.out.println("starting something");
	}

	public void end() {
		System.out.println("end something");
	}

}
