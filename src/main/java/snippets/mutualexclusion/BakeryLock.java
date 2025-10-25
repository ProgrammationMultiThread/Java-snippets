/*
 * SPDX-License-Identifier: MIT
 * Part: Algorithmes d'Exclusion Mutuelle
 * Section: Exclusion mutuelle à partir de read/write
 * Subsection: Algorithme de la Boulangerie de Lamport
 * Slide: Algorithme de Lamport
 */

package snippets.mutualexclusion;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class BakeryLock {

	private final AtomicIntegerArray entering; 
	private final AtomicIntegerArray priority;

	public BakeryLock(int n) {
		entering = new AtomicIntegerArray(n);
		priority = new AtomicIntegerArray(n);
	}

	public void lock(int i) {
		entering.set(i, 1);
		int priorityI = 1;
		for(int j = 0; j < priority.length(); j++) {
			int priorityJ = priority.get(j);
			if(priorityJ >= priorityI) priorityI = priorityJ+1;
		}
		priority.set(i, priorityI);
		entering.set(i, 0);
		for(int j = 0; j < priority.length(); j++) {
			while(entering.get(j) == 1);
			while(true) {
				int priorityJ = priority.get(j);
				if(priorityJ == 0) break;
				if(priorityI < priorityJ) break;
				if(priorityI == priorityJ && i<=j) break;
			}
		}
	}

	public void unlock(int i) {
		priority.set(i, 0);
	}
	
	public static void main(String[] args) {
		var lock = new BakeryLock(4);
		new Thread(() -> run(lock, 0)).start();
		new Thread(() -> run(lock, 1)).start();
		new Thread(() -> run(lock, 2)).start();
		new Thread(() -> run(lock, 3)).start();
	}

	public static void run(BakeryLock lock, int id) {
		lock.lock(id);
		System.out.println("Thread "+id+" entering");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		System.out.println("Thread "+id+" leaving");
		lock.unlock(id);
	}

}