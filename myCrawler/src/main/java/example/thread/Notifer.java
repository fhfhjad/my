package example.thread;

public class Notifer implements Runnable {

	private Message msg;

	public Notifer(Message msg) {
		super();
		this.msg = msg;
	}

	@Override
	public void run() {

		// while (true) {
		synchronized (msg) {
			msg.notify();
		}
		// }

	}

}
