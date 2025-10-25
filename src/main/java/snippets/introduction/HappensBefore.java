/*
 * SPDX-License-Identifier: MIT
 * Part: Du parallélisme à la concurrence
 * Section: Exécutions concurrentes
 * Subsection: Notion d'asynchronisme
 * Slide: Un premier exemple
 */

package snippets.introduction;

public class HappensBefore {

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(() -> {
			System.out.print("Hello ");
			System.out.print("World ");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.print("Multithreaded ");
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("!");
	}

}
