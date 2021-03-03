import javafx.util.Pair;

import java.util.Random;

public class Parabola extends AbstractSolver implements Solver {
    public Parabola(double leftBound, double rightBound, double epsilon) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        calcMin(false);
    }

    private Pair<Double, Double> searchX2(double x1, double x3, double fX1, double fX3) {
        Random random = new Random();
        double x2 = x1 + (x3 - x1) * random.nextDouble();
        double fX2 = calcFunc(x2);
        while (fX2 > fX1 || fX2 > fX3) {
            x2 = x1 + (x3 - x1) * random.nextDouble();
            fX2 = calcFunc(x2);
        }
        return new Pair<>(x2, fX2);
    }

    private void calcMin(boolean printSteps) {
        double x1 = leftBound, x3 = rightBound;
        double fX1 = calcFunc(x1), fX3 = calcFunc(x3);
        Pair<Double, Double> middlePoint = searchX2(x1, x3, fX1, fX3);
        double x2 = middlePoint.getKey();
        double fX2 = middlePoint.getValue();

        double a1 = (fX2 - fX1) / (x2 - x1);
        double a2 = ((fX3 - fX1) / (x3 - x1) - (fX2 - fX1) / (x2 - x1)) / (x3 - x2);

        double xMed = (x1 + x2 - a1 / a2) / 2;
        double fXMed = calcFunc(xMed);

        int k = 0;
        if (printSteps) {
            printValues(k, x1, x2, x3, fX1, fX2, fX3, a1, a2, xMed, fXMed);
            System.out.println();
        }

        while (true) {
            if (k > 0) {
                a1 = (fX2 - fX1) / (x2 - x1);
                a2 = ((fX3 - fX1) / (x3 - x1) - (fX2 - fX1) / (x2 - x1)) / (x3 - x2);
                xMed = (x1 + x2 - a1 / a2) / 2;
                fXMed = calcFunc(xMed);
                if (printSteps) {
                    printValues(k, x1, x2, x3, fX1, fX2, fX3, a1, a2, xMed, fXMed);
                    System.out.println();
                }
                if (x3 - x1 <= epsilon) {
                    minX = xMed;
                    minFunc = fXMed;
                    break;
                }
            }
            if ((x1 < xMed && xMed < x2) && (fXMed >= fX2)) {
                x1 = xMed;
                fX1 = fXMed;
            } else if ((x1 < xMed && xMed < x2) && (fXMed < fX2)) {
                x3 = x2;
                fX3 = fX2;
                x2 = xMed;
                fX2 = fXMed;
            } else if ((x2 < xMed && xMed < x3) && (fX2 >= fXMed)) {
                x1 = x2;
                fX1 = fX2;
                x2 = xMed;
                fX2 = fXMed;
            } else if ((x2 < xMed && xMed < x3) && (fX2 < fXMed)) {
                x3 = xMed;
                fX3 = fXMed;
            }
            k++;
        }
    }

    @Override
    public double getMinX() {
        return minX;
    }

    @Override
    public double getMinFunc() {
        return minFunc;
    }

    @Override
    public void printSteps() {
        calcMin(true);
    }
}
