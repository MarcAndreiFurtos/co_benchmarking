package logging;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements LoggingInterface{
    private PrintWriter logFile;

    public FileLogger(String logFilePath) {
        try {
            this.logFile = new PrintWriter(new FileWriter(logFilePath));
        } catch (IOException e) {
          System.out.println("Error opening the file.");
            e.printStackTrace();
        }
    }
    public void write(long value) {
        logFile.println(value);
    }
    public void write(String value) {
        logFile.println(value);
    }
    public void write(Object... values) {
        for (Object value : values) {
            logFile.println(value + " ");
        }
    }

    @Override
    public void close() {
        logFile.close();
    }
    public void writeTime(String message, long nanos, TimeUnit unit) {
        double time = TimeUnitHelper.convert(nanos, unit);
        write(String.format("%s %.3f %s", message, time, unit));
    }
}
