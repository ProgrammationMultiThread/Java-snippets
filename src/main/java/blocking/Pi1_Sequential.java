package blocking;

public class Pi1_Sequential {
	
	private static int experiment(int nbIterations) throws InterruptedException {
		int result = 0;
		for(int i = 0; i<nbIterations; i++) {
			double x = Math.random();
			double y = Math.random();
			if(x*x + y*y < 1) result++;
		}
		return result;
	}

	public static void main(String[] args) throws InterruptedException {
		for(int nbIterations = 1024; nbIterations<=4194304; nbIterations*=8) {
			long start = System.currentTimeMillis();
			int result = experiment(nbIterations);
			long finish = System.currentTimeMillis();
			System.out.println(
					nbIterations + " iterations,\t" 
							+ "π≃" + 4.0*result/nbIterations
							+ "\t(" + (finish - start) + "ms)");
		}
	}
	
}
