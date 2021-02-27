public class Dichotomy extends AbstractSolver implements Solver {
    private double leftBound, rightBound, minX, minFunc;
    private static final double EPSILON = 0.0001;
    private static final double DELTA = EPSILON / 2;

    public Dichotomy(double leftBound, double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        calcMinX(false);
    }

    private void calcMinX(boolean printSteps) {
        double epsilonN = 1, a = leftBound, b = rightBound;
        int count = 0;
        while (epsilonN > EPSILON) {
            double x1 = (a + b - DELTA) / 2;
            double x2 = (a + b + DELTA) / 2;
            double fX1 = calcFunc(x1);
            double fX2 = calcFunc(x2);
            if (printSteps) {
                count++;
                printValues(count, a, b, x1, x2, fX1, fX2);
            }
            if (fX1 - fX2 < 0) {
                if (printSteps) {
                    System.out.print(String.format("%.10f", (b - a) / (x2 - a)));
                    System.out.println();
                }
                b = x2;
            } else {
                if (printSteps) {
                    System.out.print(String.format("%.10f", (b - a) / (b - x1)));
                    System.out.println();
                }
                a = x1;
            }

            epsilonN = (b - a) / 2;
        }
        minX = (a + b) / 2;
        minFunc = calcFunc(minX);
    }

    public double getMinX() {
        return minX;
    }

    public double getMinFunc() {
        return minFunc;
    }

    public void printSteps() {
        calcMinX(true);
    }
}
