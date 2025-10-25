/*
 * SPDX-License-Identifier: MIT
 * Inspired from https://github.com/thibaultdelor/InvalidCodeBlog
 * Part: Synchronisation bloquante
 * Section: Synchronisation par verrous
 * Subsection: Section critique
 * Slide: Exemple de programme parallèle
 */

package snippets.memory;

public class Volatile {

	private int value = 0;

	public synchronized void increment() { 
		value++; 
	}
	
	public int get() { 
		return value; 
	}

	public static void main(String[] args) throws InterruptedException {

		var counter = new Volatile();
		
		new Thread(() -> {
			while(counter.get()==0);
			System.out.println("done");
		}).start();

		Thread.sleep(250);

		new Thread(counter::increment).start();
		
	}
	
}
