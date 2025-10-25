package blocking;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomExperiment {
	
	private static void executeParallel(int nbThread, Supplier<Runnable> runnable) throws InterruptedException {
		var threads = new Thread[nbThread];
		for(int i = 0; i<nbThread; i++) 
			threads[i] = new Thread(runnable.get());
		long start = System.currentTimeMillis();
		for(var t : threads) t.start();
		for(var t : threads) t.join();
		long finish = System.currentTimeMillis();
		System.out.println((finish - start) + "ms");
	}

	public static void main(String[] args) throws InterruptedException {
		int nbThreads = 8;
		int nbIterations = 10000000;
		
		var rand1 = new Random();
		executeParallel(nbThreads, () -> () -> {
			for(int j = 0; j<nbIterations; j++)
				rand1.nextBoolean();
		});

		executeParallel(nbThreads, () -> () -> {
			var rand2 = new Random();
			for(int j = 0; j<nbIterations; j++)
				rand2.nextBoolean();
		});

		executeParallel(nbThreads, () -> () -> {
			var rand3 = ThreadLocalRandom.current();
			for(int j = 0; j<nbIterations; j++)
				rand3.nextBoolean();
		});
	}
}
