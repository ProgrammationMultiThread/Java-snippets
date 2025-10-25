/*
 * SPDX-License-Identifier: MIT
 * Part: Synchronisation bloquante
 * Section: Synchronisation par verrous
 * Subsection: Section critique
 * Slide: Expérience
 */

package snippets.blocking;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomExperiment {

	private static long executeParallel(int nbThread, Supplier<Runnable> runnable) throws InterruptedException {
		var threads = new Thread[nbThread];
		for(int i = 0; i<nbThread; i++) {
			threads[i] = new Thread(runnable.get());
		}

		long start = System.nanoTime();
		for(var t : threads) t.start();
		for(var t : threads) t.join();
		long finish = System.nanoTime();
		return (finish - start) / 1_000_000L;
	}

	public static void main(String[] args) throws InterruptedException {
		int nbThreads = 8;
		int nbIterations = 10000000;

		// Experiment 1: one shared Random instance
		var rand1 = new Random();
		long time1 = executeParallel(nbThreads, () -> () -> {
			for(int j = 0; j<nbIterations; j++)
				rand1.nextBoolean();
		});
		System.out.printf("Random (shared)\t\t->  %4d ms\n", time1);

		// Experiment 2: one Random instance per thread
		long time2 = executeParallel(nbThreads, () -> () -> {
			var rand2 = new Random();
			for(int j = 0; j<nbIterations; j++)
				rand2.nextBoolean();
		});
		System.out.printf("Random (per-thread)\t->  %4d ms\n", time2);

		// Experiment 3: Usage of ThreadLocalRandom
		long time3 = executeParallel(nbThreads, () -> () -> {
			var rand3 = ThreadLocalRandom.current();
			for(int j = 0; j<nbIterations; j++)
				rand3.nextBoolean();
		});
		System.out.printf("ThreadLocalRandom\t->  %4d ms\n", time3);

	}
}
