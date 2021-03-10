import java.util.function.Function;

public class GoldenSection extends AbstractSolver implements Solver {
    public GoldenSection(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        this.function = function;
        createLogger("GS, eps=" + epsilon, 0);
        calcMinX();
    }

    private void calcMinX() {
        double epsilonN = 1, a = leftBound, b = rightBound;
        int count = 0;
        double x1 = a + (b - a) * TAU_2;
        double x2 = a + (b - a) * TAU_1;
        double fX1 = function.apply(x1);
        double fX2 = function.apply(x2);
        int cnt = 2;
        GoldenFibonacciImpl goldenFibonacciImpl = new GoldenFibonacciImpl(a, b, x1, x2, fX1, fX2, count, cnt, logger, function);
        while (epsilonN > epsilon) {
            epsilonN = (goldenFibonacciImpl.getB() - goldenFibonacciImpl.getA()) / 2;
            goldenFibonacciImpl.calcMinImpl(true);
        }
        cnt = goldenFibonacciImpl.getCnt();
        minX = (goldenFibonacciImpl.getA() + goldenFibonacciImpl.getB()) / 2;
        minFunc = function.apply(minX);
        cnt++;
        logger.writeCntFunc(cnt);
        logger.writeInFile();
    }
}
