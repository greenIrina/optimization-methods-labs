package oneDimensional;

import java.util.List;
import java.util.function.Function;

public class Parabola extends OneDimensionalSolver  {
    public Parabola(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        super(leftBound, rightBound, epsilon, function);
        calcMin();
    }

    private void calcMin() {
        double x1 = leftBound, x2 = (leftBound + rightBound) / 2, x3 = rightBound;
        double fX1 = function.apply(x1), fX2 = function.apply(x2), fX3 = function.apply(x3);
        int k = 0;

        List<Double> values = calcParabolaMin(x1, x2, x3, fX1, fX2, fX3);
        double xMed = values.get(2);
        double fXMed = values.get(3);

        double xPrev = xMed;

        while (true) {
            if (k > 0) {
                values = calcParabolaMin(x1, x2, x3, fX1, fX2, fX3);
                xMed = values.get(2);
                fXMed = values.get(3);

                if (Math.abs(xMed - xPrev) <= epsilon) {
                    minX = xMed;
                    minFunc = fXMed;
                    break;
                }
            }
            if (x1 < xMed && xMed < x2) {
                if (fXMed >= fX2) {
                    x1 = xMed;
                    fX1 = fXMed;
                } else {
                    x3 = x2;
                    fX3 = fX2;
                    x2 = xMed;
                    fX2 = fXMed;
                }
            } else if (x2 < xMed && xMed < x3) {
                if (fX2 >= fXMed) {
                    x1 = x2;
                    fX1 = fX2;
                    x2 = xMed;
                    fX2 = fXMed;
                } else {
                    x3 = xMed;
                    fX3 = fXMed;
                }
            }
            k++;
            xPrev = xMed;
        }
    }
}
