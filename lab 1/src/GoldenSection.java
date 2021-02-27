public class GoldenSection extends AbstractSolver implements Solver {
    private double leftBound, rightBound, minX, minFunc;
    private static final double TAU = (Math.sqrt(5) - 1) / 2;
    private static final double EPSILON = 0.0001;

    public GoldenSection(double leftBound, double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        calcMinX(false);
    }

    private void calcMinX(boolean printSteps) {
        double epsilonN = 1, a = leftBound, b = rightBound;
        int count = 0;
        while (epsilonN > EPSILON) {
            double x1 = a + (3 - Math.sqrt(5)) * (b - a) / 2;
            double x2 = a + (Math.sqrt(5) - 1) * (b - a) / 2;
            double fX1 = calcFunc(x1);
            double fX2 = calcFunc(x2);
            epsilonN = (b - a) / 2;
            if (printSteps) {
                count++;
                printValues(count, a, b, x1, x2, fX1, fX2);
            }
            if (fX1 - fX2 <= 0) {
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
        }
        minX = (a + b) / 2;
        minFunc = calcFunc(minX);
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
        calcMinX(true);
    }
}
