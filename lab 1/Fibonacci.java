import java.util.function.Function;

public class Fibonacci extends AbstractSolver implements Solver {
    private int iterationsNum;
    private double fibonacciN;

    public Fibonacci(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        this.function = function;
        iterationsNum = countIterationsNum();
        createLogger("F, eps=" + epsilon, 0);
        calcMinX();
    }

    private double fibonacciNum(int k) {
        // return Math.pow((1 + Math.sqrt(5)) / 2, k) / Math.sqrt(5);
        if (k <= 1) {
            return 1;
        } else {
            return fibonacciNum(k - 1) + fibonacciNum(k - 2);
        }
    }

    private int countIterationsNum() {
        int n = 0;
        double fibonacciN = fibonacciNum(n);
        double condition = (rightBound - leftBound) / epsilon;
        while (fibonacciN - condition <= 0) {
            n++;
            fibonacciN = fibonacciNum(n);
        }
        this.fibonacciN = fibonacciN;
        return n;
    }

    private void calcMinX() {
        double a = leftBound, b = rightBound;
        double l = (b - a) / fibonacciN;
        double x1 = a + l * fibonacciNum(iterationsNum - 2);
        double x2 = a + l * fibonacciNum(iterationsNum - 1);
        double fX1 = function.apply(x1);
        double fX2 = function.apply(x2);
        int count = 0, cnt = 2;
        GoldenFibonacciImpl goldenFibonacciImpl = new GoldenFibonacciImpl(a, b, x1, x2, fX1, fX2, count, cnt, logger, function);
        for (int k = iterationsNum - 1; k > 1; k--) {
            goldenFibonacciImpl.calcMinImpl(false);
        }
        cnt = goldenFibonacciImpl.getCnt();
        minX = (goldenFibonacciImpl.getX1() + goldenFibonacciImpl.getX2()) / 2;
        minFunc = function.apply(minX);
        cnt++;
        logger.writeCntFunc(cnt);
        logger.writeInFile();
    }
}
