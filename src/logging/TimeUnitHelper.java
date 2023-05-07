package logging;

public class TimeUnitHelper {
    public static double convert(long nanos, TimeUnit unit){
        int difference = unit.ordinal() - TimeUnit.NANOSECONDS.ordinal();
        return nanos / Math.pow(10, difference*3);
    }

    public static double convert(double time, TimeUnit from, TimeUnit to)
    {
        int difference = from.ordinal() - to.ordinal();
        return time / Math.pow(10, difference*3);
    }
}