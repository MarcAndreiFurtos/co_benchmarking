package testbench;

import logging.ConsoleLogger;
import logging.FileLogger;
import logging.LoggingInterface;
import logging.TimeUnit;
import timing.TimingInterface;
import timing.Timing;
import benchmark.BenchmarkInterface;
import benchmark.cpu.CpuFixedPoint;
import benchmark.cpu.CPUFixedvsFloatingPointTest;
import benchmark.cpu.NumberRepresentation;

public class TestCPUFixedVsFloatingPoint {

    public static void main(String[] args) {
        TimingInterface timer = new Timing();
        LoggingInterface log = /* new FileLogger("bench.log"); */new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        BenchmarkInterface bench = new CPUFixedvsFloatingPointTest();
        bench.initialize(10000000);
        bench.warmUp();

        timer.start();
        bench.run(NumberRepresentation.FIXED);
//		bench.run(NumberRepresentation.FLOATING);
        long time = timer.stop();
        log.writeTime("Finished in", time, timeUnit);
        log.write("Result is", bench.getResult());

        log.close();
    }
}