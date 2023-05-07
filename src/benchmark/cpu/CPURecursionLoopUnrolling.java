package benchmark.cpu;

import benchmark.BenchmarkInterface;

public class CPURecursionLoopUnrolling implements BenchmarkInterface {
    private int size;
    private long result;
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet. Use run(Object... params) instead.");
    }

    @Override
    public void run(Object... params) {
        boolean useLoopUnrolling = (boolean)params[0];
        int unrollingLevel = (int)params[1];
        if(useLoopUnrolling){
            recursiveUnrolled(0,size,unrollingLevel,0);
        }else{
            recursive(0,size,0);
        }
    }

    @Override
    public void initialize(Object... params) {
        size = (int)params[0];
    }

    @Override
    public void warmUp() {

    }

    public void clean() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

    private long recursive(long start,long size, int counter){
        long sum = 0;
        if(start < size){
            long nextPrime = getNextPrime(start);
            sum += nextPrime;
            try {
                sum += recursive(nextPrime + 1, size, counter + 1);
            }
            catch(StackOverflowError e){
                System.out.printf("Stack overflow at counter: %d%n",counter);
                result = sum;
            }
        }
        return sum;
    }

    private long recursiveUnrolled(long start,long size,int unrollingLevel, int counter) {
        long sum = 0;
        for(int i = 0; i < unrollingLevel && start < size;i++){
            long nextPrime = getNextPrime(start);
            sum += nextPrime;
            start = nextPrime + 1;
        }
        if(start<size) {
            try {
                sum += recursiveUnrolled(start, size, unrollingLevel, counter + 1);
            } catch (StackOverflowError e) {
                System.out.printf("Stack overflow at counter: %d%n", counter);
                result = sum;
            }
        }
        return sum;
    }

    private boolean isPrime(long number){
        if(number <= 2){
            return true;
        }
        for(int i = 2; i < Math.sqrt(number); i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    private long getNextPrime(long start){
        long nextPrime = start;
        while(!isPrime(nextPrime)){
            nextPrime++;
        }
        return nextPrime;
    }
}
