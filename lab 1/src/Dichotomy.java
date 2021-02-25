public class Dichotomy extends AbstractSolver implements Solver {
    private double leftBound, rightBound, minX, minFunc;
    private static final double EPSILON = 0.0001;
    private static final double DELTA = EPSILON / 2;

    public Dichotomy(double leftBound, double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        calcMinX();
    }

    private void calcMinX() {
        double epsilonN = 1, a = leftBound, b = rightBound;
        while (epsilonN > EPSILON) {
            double x1 = (a + b - DELTA) / 2, fX1;
            double x2 = (a + b + DELTA) / 2, fX2;
            fX1 = calcFunc(x1);
            fX2 = calcFunc(x2);
            if (fX1 < fX2) {
                b = x2;
            } else {
                a = x1;
            }
            epsilonN = (b - a) / 2;
            if (epsilonN <= EPSILON) {
                break;
            }
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
}
