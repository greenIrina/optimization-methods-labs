package oneDimensional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class OneDimensionalSolver {
    protected double leftBound, rightBound, minX, minFunc, epsilon;
    protected static final double TAU_1 = (Math.sqrt(5) - 1) / 2;
    protected static final double TAU_2 = (3 - Math.sqrt(5)) / 2;
    protected Function<Double, Double> function;

    public OneDimensionalSolver(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        this.function = function;
    }

    protected List<Double> calcParabolaMin(double x1, double x2, double x3, double fX1, double fX2, double fX3) {
        double a1, a2, xMed, fXMed;
        a1 = (fX2 - fX1) / (x2 - x1);
        a2 = ((fX3 - fX1) / (x3 - x1) - (fX2 - fX1) / (x2 - x1)) / (x3 - x2);
        xMed = (x1 + x2 - a1 / a2) / 2;
        fXMed = function.apply(xMed);
        return Arrays.asList(a1, a2, xMed, fXMed);
    }

    public double getMinX() {
        return minX;
    }

    public double getMinFunc() {
        return minFunc;
    }
}
