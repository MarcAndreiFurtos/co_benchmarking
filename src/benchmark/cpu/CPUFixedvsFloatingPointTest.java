package benchmark.cpu;

import benchmark.BenchmarkInterface;

public class CPUFixedvsFloatingPointTest implements BenchmarkInterface {
    private int result;
    private int size;

    @Override
    public void initialize(Object ... params) {
        this.size = (Integer)params[0];
    }

    @Override
    public void warmUp() {
        for (int i = 0; i < size; ++i) {
            result = i/256 ;
            result = (int) (i/256.0);
        }
    }

    @Override
    @Deprecated
    public void run() {
    }

    @Override
    public void run(Object ...options) {
        result = 0;

        switch ((NumberRepresentation) options[0]) {
            case FLOATING -> {
                for (int i = 0; i < size; ++i)
                    result += 1 / 256.0;/**/
            }
            case FIXED -> {
                for (int i = 0; i < size; ++i)
                    result += i / 256;/**/
            }
            default -> {
            }
        }

    }

    @Override
    public void cancel() {

    }
    public String getResult() {
        return String.valueOf(result);
    }
}
