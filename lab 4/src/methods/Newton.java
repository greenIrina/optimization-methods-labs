package methods;

import utils.DoubleVector;

public interface Newton {
    DoubleVector solve(DoubleVector vector);

    int getIterationsNumber();

    void printFirst();

    void printSecond();
}
