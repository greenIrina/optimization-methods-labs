public class GoldenSection extends AbstractSolver implements Solver {
    private static final double TAU = (Math.sqrt(5) - 1) / 2;

    public GoldenSection(double leftBound, double rightBound, double epsilon) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        calcMinX(false);
    }

    private void calcMinX(boolean printSteps) {
        double epsilonN = 1, a = leftBound, b = rightBound;
        int count = 0;
        double x1 = a + (3 - Math.sqrt(5)) * (b - a) / 2;
        double x2 = a + (Math.sqrt(5) - 1) * (b - a) / 2;
        double fX1 = calcFunc(x1);
        double fX2 = calcFunc(x2);
        GoldenFibonacciImpl goldenFibonacciImpl = new GoldenFibonacciImpl(a, b, x1, x2, fX1, fX2, TAU, count, printSteps);
        while (epsilonN > epsilon) {
            epsilonN = (goldenFibonacciImpl.getB() - goldenFibonacciImpl.getA()) / 2;
            goldenFibonacciImpl.calcMinImpl(true);
        }
        minX = (goldenFibonacciImpl.getA() + goldenFibonacciImpl.getB()) / 2;
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
