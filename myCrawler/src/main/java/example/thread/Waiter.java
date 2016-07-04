/**   
 * @Title: Waiter.java
 * @Package example
 * @Description: TODO
 * @author sunlulu   
 * @date 2016年1月25日 下午5:58:34
 * @version V1.0   
 */
package example.thread;

/**
 * @ClassName: Waiter
 * @Description: TODO
 * @author sunlulu
 * @date 2016年1月25日 下午5:58:34
 */

public class Waiter implements Runnable {

	private Message msg;

	public Waiter(Message msg) {
		super();
		this.msg = msg;
	}

	@Override
	public void run() {
		String nameString = Thread.currentThread().getName();
		// while (true) {
		synchronized (msg) {

			System.out.println(nameString + " waiter ..."
					+ System.currentTimeMillis());
			try {
				msg.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(nameString + " waiter end"
					+ System.currentTimeMillis());
		}
		// }
	}

}
