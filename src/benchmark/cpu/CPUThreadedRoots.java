package benchmark.cpu;

import benchmark.BenchmarkInterface;

public class CPUThreadedRoots implements BenchmarkInterface {

    private double result;
    private int size;
    private boolean running;

    @Override
    public void initialize(Object... params) {
       size = (int)params[0];
    }

    @Override
    public void warmUp() {
        // call run method: call run() once
        // detect number of cores: Runtime.....availableProcessors();
        int cores = Runtime.getRuntime().availableProcessors();
        run(cores);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Method not implemented. Use run(Objects...) instead");
    }

    @Override
    public void run(Object... options) {
        int nThreads = (int)options[0];

        Thread[] threads = new Thread[nThreads];

        // e.g. 1 to 10,000 on 4 threads = 2500 jobs per thread
        final int jobPerThread = 0; /**/

        running = true; // flag used to stop all started threads
        // create a thread for each runnable (SquareRootTask) and start it
        for (int i = 0; i < nThreads; ++i) {
            threads[i] = new Thread(new SquareRootTask(i*jobPerThread , (i+1)*jobPerThread));
            threads[i].start();
        }

        // join threads
        for (int i = 0; i < nThreads; ++i) {
            try{
             threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancel() {
        running = false;
    }

    public void clean() {
        // only implement if needed
    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    class SquareRootTask implements Runnable {

        private int from, to;
        private final double precision = 1e-4; // fixed
        private double result = 0.0;

        public SquareRootTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public void run() {
            // compute Newtonian square root on each number from i = 'from' to 'to', and also check 'running'
            // save (+=) each computed square root in the local 'result' variable
            // extra: send 'result' back to main thread and sum up with all results
            for(int i = from ; i< to && running ; i++){
               result += getNewtonian(i);
            }
        }

        private double getNewtonian(double x) {
            if (x < 0) {
                throw new IllegalArgumentException("Cannot calculate square root of negative number");
            }

            double guess = x / 2.0;
            double prevGuess;

            do {
                prevGuess = guess;
                guess = (guess + x / guess) / 2.0;
            } while (Math.abs(guess - prevGuess) > 0.00001);

            return guess;
        }

        // extra: compute sum, pass it back to wrapper class. Use synchronized
    }

}