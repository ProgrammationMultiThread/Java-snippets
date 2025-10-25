package blocking;

import java.util.*;
import java.util.concurrent.*;


public class Pi6_ThreadPool {

	private static int result;
	
	private static int experiment(int nbThreads, int nbIterationsPerThread) throws InterruptedException, ExecutionException {
		result = 0;
		Executors.newCachedThreadPool().invokeAll(Collections.nCopies(nbThreads, () -> {
			var random = ThreadLocalRandom.current();
			int local_result = 0;
			for(int j = 0; j<nbIterationsPerThread; j++) {
				double x = random.nextDouble();
				double y = random.nextDouble();
				if(x*x + y*y < 1) local_result++;
			}
			synchronized(Pi6_ThreadPool.class) {
				result+=local_result;
			}
			return null;
		}));
		return result;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		for(int nbIterations = 1024; nbIterations<=4194304; nbIterations*=8) {
			for(int nbThreads = 1; nbThreads<=1024; nbThreads*=2) {
				long start = System.currentTimeMillis();
				int result = experiment(nbThreads, nbIterations/nbThreads);
				long finish = System.currentTimeMillis();
				System.out.println(nbThreads + " tâches,\t" 
						+ nbIterations + " iterations,\t" 
						+ "π≃" + 4.0*result/((nbIterations/nbThreads)*nbThreads)
						+ "\t(" + (finish - start) + "ms)");
			}
			System.out.println();
		}
	}
}
