
package benchmark.cpu;

import benchmark.BenchmarkInterface;

public class CpuFixedPoint implements BenchmarkInterface {
    private int size;
    @Override
    public void run() {
        IntegerArithmeticTest();
        BranchingTest();
        ArrayAccessTest();
    }

    @Override
    public void run(Object... params) {
        int option = (int)params[0];
        switch (option) {
            case 0 -> IntegerArithmeticTest();
            case 1 -> BranchingTest();
            case 2 -> ArrayAccessTest();
        }
    }

    @Override
    public void initialize(Object... params) {
        size = (int)params[0];
    }

    @Override
    public void warmUp() {
        IntegerArithmeticTest();
        BranchingTest();
        ArrayAccessTest();
    }

    public void clean() {
        size = 0;
    }

    @Override
    public void cancel() {

    }

    @Override
    public String getResult() {
        return null;
    }

    private void IntegerArithmeticTest(){
        int a = 0;
        int b = 0;
        int c = 0;
        for(int i=0;i<size;i++){
            a = a + 1;
            b = a - 1;
            c = b * 2;
            a = a + c / 2;
        }
    }

    private void BranchingTest() {
        int[] a = {0, 1, 2, 3};
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (j == 1) {
                j = a[2];
            } else {
                j = a[3];
            }
            if (j > 2) {
                j = a[0];
            } else {
                j = a[1];
            }
            if (j < 1) {
                j = a[1];
            } else {
                j = a[0];
            }
        }
    }

    private void ArrayAccessTest() {
        int[] a = new int[size];
        int[] b = new int[size];
        int[] c = new int[size];

        for (int i = 0; i < size; i++) {
            c[i] = a[b[i]];
        }
    }
}