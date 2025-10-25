/*
 * SPDX-License-Identifier: MIT
 * Part: Synchronisation bloquante
 * Section: Synchronisation par verrous
 * Subsection: Utilisation des verrous
 * Slide: Exemple
 */

package snippets.blocking;

import java.util.concurrent.ThreadLocalRandom;

public class Pi5_Independant {

	private static int inside = 0;
	private static final Object lock = new Object();
	
	private static int experiment(int nbThreads, int nbIterations) throws InterruptedException {
		Thread[] threads = new Thread[nbThreads];

		for(int i = 0; i<nbThreads; i++)
			threads[i] = new Thread( () -> {
				int local_inside = 0;
				var random = ThreadLocalRandom.current();
				for(int j = 0; j<nbIterations/nbThreads; j++) {
					double x = random.nextDouble();
					double y = random.nextDouble();
					if(x*x + y*y < 1) local_inside++;
				}
				synchronized(lock) {
					inside+=local_inside;
				}
			});
		
		inside = 0;
		for(var t : threads) t.start();
		for(var t : threads) t.join();
		return inside;
	}
		
	public static void main(String[] args) throws InterruptedException {
		for(int nbIterations = 1024; nbIterations<=4194304; nbIterations*=8) {
			for(int nbThreads = 1; nbThreads<=1024; nbThreads*=2) {
				long t0 = System.nanoTime();
				int inside = experiment(nbThreads, nbIterations);
				long dtMs = (System.nanoTime() - t0) / 1_000_000L;
				double pi = 4.0 * inside / nbIterations;

				System.out.printf("%d iterations,\t%d threads,\tπ ≃ %.6f\t(%d ms)%n",
						nbIterations, nbThreads, pi, dtMs);
			}
			System.out.println();
		}
	}
}
