package timing;

//implementation of the methods in the timing interface

public class Timing implements TimingInterface{
    // elapsed time resents at pause and stop so we need total time to sum up all the time elapsed
    private long start;
    private long stop;
    private long elapsedTime;
    private long totalTime;
    public Timing(){
        start = 0;
        stop = 0;
        elapsedTime = 0;
        totalTime = 0;
    }
    public void start(){
        start = 0;
        stop = 0;
        elapsedTime = 0;
        totalTime = 0;
        start = System.nanoTime();
    }
    public long stop(){
        stop = System.nanoTime();
        elapsedTime = stop - start;
        totalTime += elapsedTime;
        return totalTime;
    }
    public void resume(){
        start = System.nanoTime();
    }

    public long pause(){
        stop = System.nanoTime();
        elapsedTime = stop - start;
        totalTime += elapsedTime;
        return elapsedTime;
    }

}
