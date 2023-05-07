package benchmark.cpu;
import benchmark.BenchmarkInterface;

import java.util.Random;

public class DemoBenchmark implements BenchmarkInterface {
    int[] arr;
    Integer n;

    public DemoBenchmark() {

    }
    @Override
    public void initialize(Object... params) {
        Random random = new Random();
        this.n = (Integer)params[0];
        this.arr = new int[n];
        for(int i = 0 ; i<n ; i++){
            arr[i]= random.nextInt(1000);
        }
    }

    @Override
    public void warmUp() {

    }

    @Override
    public void run() {
            for (int i = 0; i < n - 1; i++)
                for (int j = 0; j < n - i - 1; j++)
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
    }

    @Override
    public void run(Object... params) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult() {
        return null;
    }
}
