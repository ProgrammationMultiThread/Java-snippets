package mutualexclusion;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {
	
	private AtomicBoolean taken = new AtomicBoolean(false);
	
	public void lock() {
		while(taken.getAndSet(true));
	}
	
	public void unlock() {
		taken.set(false);
	}

	
	
	public static void main(String[] args) {
		var lock = new SpinLock();
		new Thread(() -> run(lock, 0)).start();
		new Thread(() -> run(lock, 1)).start();
		new Thread(() -> run(lock, 2)).start();
		new Thread(() -> run(lock, 3)).start();
	}
	
	public static void run(SpinLock lock, int id) {
		lock.lock();
		System.out.println("Thread "+id+" entering");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		System.out.println("Thread "+id+" leaving");
		lock.unlock();
	}
	
	
}