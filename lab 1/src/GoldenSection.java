public class GoldenSection extends AbstractSolver implements Solver {
    public GoldenSection(double leftBound, double rightBound, double epsilon) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        createLogger("GS, eps=" + epsilon, 0);
        calcMinX();
    }

    private void calcMinX() {
        double epsilonN = 1, a = leftBound, b = rightBound;
        int count = 0;
        double x1 = a + (b - a) * TAU_2;
        double x2 = a + (b - a) * TAU_1;
        double fX1 = calcFunc(x1);
        double fX2 = calcFunc(x2);
        int cnt = 2;
        GoldenFibonacciImpl goldenFibonacciImpl = new GoldenFibonacciImpl(a, b, x1, x2, fX1, fX2, count, cnt, logger);
        while (epsilonN > epsilon) {
            epsilonN = (goldenFibonacciImpl.getB() - goldenFibonacciImpl.getA()) / 2;
            goldenFibonacciImpl.calcMinImpl(true);
        }
        cnt = goldenFibonacciImpl.getCnt();
        minX = (goldenFibonacciImpl.getA() + goldenFibonacciImpl.getB()) / 2;
        minFunc = calcFunc(minX);
        cnt++;
        logger.writeCntFunc(cnt);
        logger.writeInFile();
    }
}
