package testbench;
import benchmark.BenchmarkInterface;
import benchmark.cpu.CPURecursionLoopUnrolling;
import logging.ConsoleLogger;
import logging.LoggingInterface;
import logging.TimeUnit;
import logging.TimeUnitHelper;
import timing.*;

public class TestLoopUnrolling {
    public static void main(String[] args) {
        BenchmarkInterface bench = new CPURecursionLoopUnrolling();
        TimingInterface timer = new Timing();
        LoggingInterface logger = new ConsoleLogger();
        int size = 200000;
        int unrollingLevel = 1;
        boolean useLoopUnrolling = true;
        bench.initialize(size);
        timer.start();
        bench.run(useLoopUnrolling,unrollingLevel);
        long time = timer.stop();
        double timeMs = TimeUnitHelper.convert(time,TimeUnit.MILLISECONDS);
        int result = Integer.parseInt(bench.getResult());
        logger.write(timeMs);
        logger.write(result);
        double score = result/timeMs;
        logger.write(String.format("%.2f",score));
        }
}

