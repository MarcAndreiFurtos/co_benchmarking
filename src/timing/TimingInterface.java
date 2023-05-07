package timing;

public interface TimingInterface {
    /**
     * the start method resets all the variables and starts System.nanotime();
     */
    public void start();
    /**
     * the stop function stops the nano time  calculates the elapsed time and adds it to totaltime
     * @return total time
     */
    public long stop();
    /**
     * the resume function starts System.nanotime without reseting
     */
    public void resume();
    /**
     * the pause function does the same thing as the stop but returning elapsed time
     * @return elapsedTime
     */
    public  long pause();
}
