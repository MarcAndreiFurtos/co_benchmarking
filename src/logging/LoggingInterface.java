package logging;

public interface LoggingInterface {
    /**
     * prints the long parameter passed
     * @param printable
     */
    void write(long printable);
    /**
     * prints the String parameter passed
     * @param printable
     */
    void write(String printable);
    /**
     * prints the objects passed to the function
     * @param values
     */
    void write(Object ... values);

    /**
     * used to close (if necessary) any open stream (connection) used
     * for writing.
     */
    void close();

    public void writeTime(String message, long time, TimeUnit unit);
}
