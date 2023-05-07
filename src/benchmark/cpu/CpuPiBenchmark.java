package benchmark.cpu;
import benchmark.BenchmarkInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
public class CpuPiBenchmark implements BenchmarkInterface {
    boolean cancel=false;
    int precision;
    public CpuPiBenchmark() {

    }


    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void initialize(Object... params) {
        if (params.length > 0) {
            precision = (int) params[0]; // first argument is precision
        }
    }

    @Override
    public void warmUp() {
        for (int i =0;i<5;i++){
            run();
        }
    }

    @Override
    public void run() {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.ONE.divide(BigDecimal.valueOf(Math.sqrt(2)), precision, RoundingMode.HALF_UP);
        BigDecimal t = BigDecimal.valueOf(0.25);
        BigDecimal p = BigDecimal.ONE;

        for (int i = 0; i < precision; i++) {
            if(cancel){
                break;
            }
            BigDecimal aNext = a.add(b).divide(BigDecimal.valueOf(2), precision, RoundingMode.HALF_UP);
            BigDecimal bNext = BigDecimal.valueOf(Math.sqrt(a.doubleValue() * b.doubleValue())).setScale(precision, RoundingMode.HALF_UP);
            BigDecimal tNext = t.subtract(p.multiply(a.subtract(aNext).pow(2))).setScale(precision, RoundingMode.HALF_UP);
            BigDecimal pNext = p.multiply(BigDecimal.valueOf(2)).setScale(precision, RoundingMode.HALF_UP);

            a = aNext;
            b = bNext;
            t = tNext;
            p = pNext;
        }

        BigDecimal sum = a.add(b).pow(2).setScale(precision, RoundingMode.HALF_UP);
        BigDecimal pi = sum.divide(t.multiply(BigDecimal.valueOf(4)), precision, RoundingMode.HALF_UP);

    }

    @Override
    public void run(Object... params) {
            if (params.length > 0) {
                precision = (int) params[0]; // first argument is precision
            }

            BigDecimal a = BigDecimal.ONE;
            BigDecimal b = BigDecimal.ONE.divide(BigDecimal.valueOf(Math.sqrt(2)), precision, RoundingMode.HALF_UP);
            BigDecimal t = BigDecimal.valueOf(0.25);
            BigDecimal p = BigDecimal.ONE;

            for (int i = 0; i < precision; i++) {
                if(cancel){
                    break;
                }
                BigDecimal aNext = a.add(b).divide(BigDecimal.valueOf(2), precision, RoundingMode.HALF_UP);
                BigDecimal bNext = BigDecimal.valueOf(Math.sqrt(a.doubleValue() * b.doubleValue())).setScale(precision, RoundingMode.HALF_UP);
                BigDecimal tNext = t.subtract(p.multiply(a.subtract(aNext).pow(2))).setScale(precision, RoundingMode.HALF_UP);
                BigDecimal pNext = p.multiply(BigDecimal.valueOf(2)).setScale(precision, RoundingMode.HALF_UP);

                a = aNext;
                b = bNext;
                t = tNext;
                p = pNext;
            }

            BigDecimal sum = a.add(b).pow(2).setScale(precision, RoundingMode.HALF_UP);
            BigDecimal pi = sum.divide(t.multiply(BigDecimal.valueOf(4)), precision, RoundingMode.HALF_UP);
    }

    @Override
    public void cancel() {
        cancel = true;
        System.out.println("run canceled");
    }
}
