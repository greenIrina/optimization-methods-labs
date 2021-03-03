public class Dichotomy extends AbstractSolver implements Solver {
    private double delta;

    public Dichotomy(double leftBound, double rightBound, double epsilon) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        this.delta = epsilon / 2;
        calcMinX(false);
    }

    private void calcMinX(boolean printSteps) {
        double epsilonN = 1, a = leftBound, b = rightBound;
        int count = 0;
        while (epsilonN > epsilon) {
            double x1 = (a + b - delta) / 2;
            double x2 = (a + b + delta) / 2;
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
