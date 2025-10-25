package blocking;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class Pi5_Independant {

	private static int result = 0;
	
	private static int experiment(int nbThreads, int nbIterations) throws InterruptedException {
		Thread threads[] = new Thread[nbThreads];

		for(int i = 0; i<nbThreads; i++)
			threads[i] = new Thread( () -> {
				int local_result = 0;
				var random = ThreadLocalRandom.current();
				for(int j = 0; j<nbIterations/nbThreads; j++) {
					double x = random.nextDouble();
					double y = random.nextDouble();
					if(x*x + y*y < 1) local_result++;
				}
				synchronized(Pi4_Synchronized.class) {
					result+=local_result;
				}
			});
		
		result = 0;
		for(var t : threads) t.start();
		for(var t : threads) t.join();
		return result;
	}
		
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		for(int nbIterations = 1024; nbIterations<=4194304; nbIterations*=8) {
			for(int nbThreads = 1; nbThreads<=1024; nbThreads*=2) {
				long start = System.currentTimeMillis();
				int result = experiment(nbThreads, nbIterations);
				long finish = System.currentTimeMillis();
				System.out.println(nbThreads + " threads,\t" 
						+ nbIterations + " iterations,\t" 
						+ "π≃" + 4.0*result/nbIterations
						+ "\t(" + (finish - start) + "ms)");
			}
			System.out.println();
		}
	}
}
