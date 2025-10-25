/*
 * SPDX-License-Identifier: MIT
 * Part: Synchronisation bloquante
 * Section: Synchronisation par verrous
 * Subsection: Utilisation des verrous
 * Slide: Exemple
 */

package snippets.blocking;

import java.util.*;
import java.util.concurrent.*;

public class Pi6_Collect {
	
    private static int experiment(int nbThreads, int nbIterations) throws InterruptedException, ExecutionException {

        Collection<Callable<Integer>> tasks = Collections.nCopies(nbThreads, () -> {
            var random = ThreadLocalRandom.current();
            int local_inside = 0;
            for (int j = 0; j < nbIterations/nbThreads; j++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                if (x * x + y * y < 1) local_inside++;
            }
            return local_inside;
        });

        ExecutorService threadpool = Executors.newFixedThreadPool(nbThreads);
        int inside = 0;
        for (Future<Integer> f : threadpool.invokeAll(tasks)) {
            inside += f.get();
        }
        threadpool.shutdown();
        threadpool.awaitTermination(1, TimeUnit.MINUTES);
        return inside;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int nbIterations = 1_024; nbIterations <= 4_194_304; nbIterations *= 8) {
            for (int nbThreads = 1; nbThreads <= 1_024; nbThreads *= 2) {
                long t0 = System.nanoTime();
                int inside = experiment(nbThreads, nbIterations);
                long dtMs = (System.nanoTime() - t0) / 1_000_000L;
				double pi = 4.0 * inside / nbIterations;
                System.out.printf("%d iters/thread,\t%d threads,\tπ ≃ %.6f\t(%d ms)%n",
                        nbIterations, nbThreads, pi, dtMs);
            }
            System.out.println();
        }
    }
}
