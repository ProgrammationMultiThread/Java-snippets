package introduction;


public class HappensBefore {

	public static void main( String args[] ) throws InterruptedException {

		var t1 = new Thread(() -> {
			System.out.print("Hello ");
			System.out.print("World ");
		});
		
		var t2 = new Thread(() -> {
			System.out.print("Multithreaded ");
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println( "!" );
	}

}
