package logging;

import java.util.Arrays;

public class ConsoleLogger implements LoggingInterface {

    public ConsoleLogger() {

    }

    public void write(long printable){
        System.out.println(printable);
    }
    public void write(String printable){
        System.out.println(printable);
    }
    public void write(Object...values) {
        for (Object value : values)
            System.out.print(value + " ");
        System.out.println();
    }
    public void close() {
        System.out.println("nothing to close for console logging");
    }
    @Override
    public void writeTime(String message, long nanos, TimeUnit unit) {
        double time = TimeUnitHelper.convert(nanos, unit);
        write(String.format("%s %.3f %s", message, time, unit));
    }
}
