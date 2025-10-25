package mutualexclusion;

import java.util.concurrent.atomic.AtomicBoolean;

public class PetersonLock {
	
	private AtomicBoolean[] entering = new AtomicBoolean[]{new AtomicBoolean(false), new AtomicBoolean(false)};
	private volatile int priority = 0;
	
	public void lock(int i) {
		entering[i].set(true);
		priority = 1-i;
		while(entering[1-i].get() && priority != i);
	}
	
	public void unlock(int i) {
		entering[i].set(false);
	}

	
	
	
	
	public static void main(String[] args) {
		var lock = new PetersonLock();
		new Thread(() -> run(lock, 0)).start();
		new Thread(() -> run(lock, 1)).start();
	}
	
	public static void run(PetersonLock lock, int id) {
		lock.lock(id);
		System.out.println("Thread "+id+" entering");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		System.out.println("Thread "+id+" leaving");
		lock.unlock(id);
	}
	
	
}