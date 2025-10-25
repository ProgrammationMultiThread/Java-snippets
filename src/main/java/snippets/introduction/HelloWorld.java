/*
 * SPDX-License-Identifier: MIT
 * Part: Du parallélisme à la concurrence
 * Section: Programmation parallèle
 * Subsection: Gestion des threads
 * Slide: Thread et contexte d'exécution
 */

package snippets.introduction;

public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {

		String[] messages = { "Hello", "Multithreaded", "World" };
		Thread[] threads = new Thread[messages.length];
		
		for(int i = 0; i < messages.length; i++) {
			Runnable task = new Printer(messages[i]);
			threads[i] = new Thread(task);
		}

		for(Thread t : threads) t.start();
		for(Thread t : threads) t.join();

		System.out.println( "!" );

	}

}

class Printer implements Runnable {

	private final String message;

	public Printer(String message) {
		this.message = message;
	}

	public void run() {
		System.out.println(message);
	}

}
