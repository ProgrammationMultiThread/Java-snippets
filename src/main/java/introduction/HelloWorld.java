package introduction;

public class HelloWorld implements Runnable {

	private final String message;
	
	public HelloWorld(String message) {
		this.message = message;
	}
	
	public void run() {
		System.out.println(message);
	}
	
	public static void main(String[] args) throws InterruptedException {

		String messages[] = {"Hello", "Multithreaded", "World"};
		Thread threads[] = new Thread[messages.length];

		for(int i = 0; i < messages.length; i++) {
			threads[i] = new Thread(new HelloWorld(messages[i]));
		}
			
		for(Thread t : threads) t.start();
		for(Thread t : threads) t.join();

		System.out.println( "!" );
		
	}
	
}
