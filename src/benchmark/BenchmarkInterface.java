package benchmark;

public interface BenchmarkInterface {
    /**
     * this method takes the expected time and tests the timing methodssa
     * @param params
     */
    void initialize(Object...params);

    /**
     * this method runs the benchmark
     */
    void run();
    /**
     * this method runs the benchmark
     */
    void run(Object...params);
    void cancel();
    void warmUp();
    String getResult();
}
