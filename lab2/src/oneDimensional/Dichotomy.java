package oneDimensional;

import java.util.function.Function;

public class Dichotomy extends OneDimensionalSolver {
    private final double delta;


    public Dichotomy(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        super(leftBound, rightBound, epsilon, function);
        this.delta = epsilon / 2;
        calcMinX();
    }

    private void calcMinX() {
        double epsilonN = 1, a = leftBound, b = rightBound;
        while (epsilonN > epsilon) {
            double x1 = (a + b - delta) / 2;
            double x2 = (a + b + delta) / 2;
            double fX1 = function.apply(x1);
            double fX2 = function.apply(x2);
            if (fX1 - fX2 < 0) {
                b = x2;
            } else {
                a = x1;
            }

            epsilonN = (b - a) / 2;
        }
        minX = (a + b) / 2;
        minFunc = function.apply(minX);
    }
}
