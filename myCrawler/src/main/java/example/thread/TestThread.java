package example.thread;

public class TestThread {

	public static void main(String[] args) {

		Message msgMessage = new Message();
		Thread t1 = new Thread(new Waiter(msgMessage), "w1");
		Thread t2 = new Thread(new Waiter(msgMessage), "w2");

		Thread n1 = new Thread(new Notifer(msgMessage), "n1");

		t1.start();
		t2.start();
		n1.start();

		System.out.println("all thread run");

		while (true) {

			System.out.println("active thread sum:" + Thread.activeCount());
		}

	}

}
