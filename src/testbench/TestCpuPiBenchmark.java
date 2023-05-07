package testbench;

import benchmark.BenchmarkInterface;
import benchmark.cpu.CpuPiBenchmark;
import logging.ConsoleLogger;
import logging.LoggingInterface;
import logging.TimeUnit;
import timing.Timing;

public class TestCpuPiBenchmark {
    public static void main(String[] args) {
        Timing timer = new Timing();
        LoggingInterface logger = new ConsoleLogger();
        BenchmarkInterface benchmark = new CpuPiBenchmark();
        benchmark.initialize(1000);
        logger.write("Warming up");
        benchmark.warmUp();
        logger.write("Running");
        timer.start();
        for (int i = 0; i < 12; i++) {
            timer.resume();
            benchmark.run();
            long time = timer.pause();
            logger.writeTime(String.format("Run %d: ", i), time, TimeUnit.MILLISECONDS);
        }
        long time = timer.stop();
        logger.writeTime("Finished in: ", time, TimeUnit.MILLISECONDS);
    }
}
