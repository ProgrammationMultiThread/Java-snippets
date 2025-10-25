/*
 * SPDX-License-Identifier: MIT
 * Part: Synchronisation bloquante
 * Section: Synchronisation par verrous
 * Subsection: Section critique
 * Slide: Exemple de programme parallèle
 */

package snippets.blocking;

public class Pi1_Sequential {

	private static int experiment(int nbIterations) {
		int inside = 0;
		for(int i = 0; i<nbIterations; i++) {
			double x = Math.random();
			double y = Math.random();
			if(x*x + y*y < 1) inside++;
		}
		return inside;
	}

	public static void main(String[] args) {

		for(int nbIterations = 1024; nbIterations<=4194304; nbIterations*=8) {
			long t0 = System.nanoTime();
			int inside = experiment(nbIterations);
			long dtMs = (System.nanoTime() - t0) / 1_000_000L;
			double pi = 4.0 * inside / nbIterations;

			System.out.printf("%d iterations,\tπ ≃ %.6f\t(%d ms)%n",
					nbIterations, pi, dtMs);
		}

	}

}
