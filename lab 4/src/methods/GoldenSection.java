package methods;

import java.util.function.Function;

public class GoldenSection {
    private final double leftBound;
    private final double rightBound;
    private double minX;
    private final double epsilon;
    private static final double TAU_1 = (Math.sqrt(5) - 1) / 2;
    private static final double TAU_2 = (3 - Math.sqrt(5)) / 2;
    private final Function<Double, Double> function;

    public GoldenSection(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        this.function = function;
        calcMinX();
    }

    private void calcMinX() {
        double epsilonN = 1, a = leftBound, b = rightBound;
        double x1 = a + (b - a) * TAU_2;
        double x2 = a + (b - a) * TAU_1;
        double fX1 = function.apply(x1);
        double fX2 = function.apply(x2);
        while (epsilonN > epsilon) {
            if (fX1 - fX2 <= 0) {
                b = x2;
                x2 = x1;
                fX2 = fX1;
                x1 = b - TAU_1 * (b - a);
                fX1 = function.apply(x1);
            } else {
                a = x1;
                x1 = x2;
                fX1 = fX2;
                x2 = a + TAU_1 * (b - a);
                fX2 = function.apply(x2);
            }
            epsilonN = (b - a) / 2;
        }
        minX = (a + b) / 2;
    }

    public double findMin() {
        return minX;
    }
}
