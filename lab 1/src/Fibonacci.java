public class Fibonacci extends AbstractSolver implements Solver {
    private int iterationsNum;
    private double fibonacciN2;

    public Fibonacci(double leftBound, double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        iterationsNum = countIterationsNum();
        calcMinX(false);
    }

    private double fibonacciNum(int k) {
        return  Math.pow((1 + Math.sqrt(5)) / 2, k) / Math.sqrt(5);
    }

    private int countIterationsNum() {
        int n = 0;
        double fibN2 = fibonacciNum(n + 2);
        double condition = (rightBound - leftBound) / EPSILON;
        while (fibN2 - condition <= 0) {
            n++;
            fibN2 = fibonacciNum(n + 2);
        }
        fibonacciN2 = fibN2;
        return n;
    }

    private void calcMinX(boolean printSteps) {
        double a = leftBound, b = rightBound;
        double x1 = a + fibonacciNum(iterationsNum) * (b - a) / fibonacciN2;
        double x2 = a + fibonacciNum(iterationsNum + 1) * (b - a) / fibonacciN2;
        double fX1 = calcFunc(x1);
        double fX2 = calcFunc(x2);
        int count = 0;
        for (int k = 1; k <= iterationsNum; k++) {
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
                x2 = x1;
                fX2 = fX1;
                x1 = a + fibonacciNum(iterationsNum - k + 1) * (rightBound - leftBound) / fibonacciN2;
                fX1 = calcFunc(x1);
            } else {
                if (printSteps) {
                    System.out.print(String.format("%.10f", (b - a) / (b - x1)));
                    System.out.println();
                }
                a = x1;
                x1 = x2;
                fX1 = fX2;
                x2 = a + fibonacciNum(iterationsNum - k + 2) * (rightBound - leftBound) / fibonacciN2;
                fX2 = calcFunc(x2);
            }
        }
        minX = (b + a) / 2;
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
