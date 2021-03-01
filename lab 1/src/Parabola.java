public class Parabola extends AbstractSolver implements Solver {

    public Parabola(double leftBound, double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        calcMin(false);
    }

    private void searchX() {
        // TODO
    }

    private void calcMin(boolean printSteps) {
        double x1 = 6.5, x2 = 8, x3 = 9.5;
        double fX1 = calcFunc(x1), fX2 = calcFunc(x2), fX3 = calcFunc(x3);
        double a1 = (fX2 - fX1) / (x2 - x1);
        double a2 = ((fX3 - fX1) / (x3 - x1) - (fX2 - fX1) / (x2 - x1)) / (x3 - x2);
        double xMed = (x1 + x2 - a1 / a2) / 2;
        double fXMed = calcFunc(xMed);
        double xPrev = xMed;
        int k = 0;
        if (printSteps) {
            printValues(k, x1, x2, fX1, fX2, a1, a2, xMed, fXMed);
            System.out.println();
        }
        while (true) {
            if (k > 0) {
                a1 = (fX2 - fX1) / (x2 - x1);
                a2 = ((fX3 - fX1) / (x3 - x1) - (fX2 - fX1) / (x2 - x1)) / (x3 - x2);
                xMed = (x1 + x2 - a1 / a2) / 2;
                fXMed = calcFunc(xMed);
                double delta = Math.abs(xMed - xPrev);
                if (printSteps) {
                    printValues(k, x1, x2, fX1, fX2, a1, a2, xMed, fXMed);
                    System.out.println();
                }
                if (delta <= EPSILON) {
                    minX = xMed;
                    minFunc = calcFunc(xMed);
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
            xPrev = xMed;
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
