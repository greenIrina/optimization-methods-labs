package oneDimensional;

import java.util.function.Function;

public class GoldenSection extends OneDimensionalSolver {
    public GoldenSection(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        super(leftBound, rightBound, epsilon, function);
        calcMinX();
    }

    private void calcMinX() {
        double epsilonN = 1, a = leftBound, b = rightBound;
        double x1 = a + (b - a) * TAU_2;
        double x2 = a + (b - a) * TAU_1;
        double fX1 = function.apply(x1);
        double fX2 = function.apply(x2);
        GoldenFibonacciImpl goldenFibonacciImpl = new GoldenFibonacciImpl(a, b, x1, x2, fX1, fX2, function);
        while (epsilonN > epsilon) {
            epsilonN = (goldenFibonacciImpl.getB() - goldenFibonacciImpl.getA()) / 2;
            goldenFibonacciImpl.calcMinImpl(true);
        }
        minX = (goldenFibonacciImpl.getA() + goldenFibonacciImpl.getB()) / 2;
        minFunc = function.apply(minX);
    }
}
