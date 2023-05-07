package testbench;

import benchmark.BenchmarkInterface;
import benchmark.cpu.CpuFixedPoint;
import logging.ConsoleLogger;
import logging.LoggingInterface;
import logging.TimeUnit;
import logging.TimeUnitHelper;
import timing.TimingInterface;
import timing.Timing;

public class TestCPUFixedPoint {
    public static void main(String[] args) {
        LoggingInterface logger = new ConsoleLogger();
        TimingInterface timer = new Timing();
        BenchmarkInterface benchmark = new CpuFixedPoint();
        int size = 1_000_000_00;
        benchmark.initialize(size);
        logger.write("Warming up");
        benchmark.warmUp();
        logger.write("Running");
        timer.start();
        benchmark.run(0);
        long time = timer.stop();

        int operations = 13;
        double timeSeconds = TimeUnitHelper.convert(time, TimeUnit.SECONDS);
        double MOPS = (size * (double)operations) / (timeSeconds *1e6);

        logger.writeTime("Finished in ", time, TimeUnit.MILLISECONDS);
        logger.write(String.format("MOPS: %.2f", MOPS));
    }
}