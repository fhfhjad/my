package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MyExcutorPool {

	public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	public static ExecutorService pool = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		final MyExcutorPool myExcutorPool = new MyExcutorPool();

		System.out.println("开始执行>>>>>>>>>>>>>");

		myExcutorPool.product(); // 生产者
		myExcutorPool.xiaofei(); // 创建10个线程

	}

	private void product() {

		pool.execute(new Thread(new Runnable() {
			@Override
			public void run() {

				while (true) {
					if (queue.size() == 0) {
						for (int i = 0; i < 100; i++) {
							queue.add(i + "");
							try {
								Thread.sleep(1500l);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(i);
						}
						System.out.println("数据生产完成");
					}
				}

			}
		}, "thread-product"));
	}

	private void xiaofei() {
		for (int i = 0; i < 10; i++) {
			pool.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							String dataString = "<" + queue.take() + ">";
							System.out.println("线程"
									+ Thread.currentThread().getName() + "处理"
									+ dataString + "完成的" + queue.size());
							Thread.sleep(1000l);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}, "thread-" + i));
		}
	}

}
