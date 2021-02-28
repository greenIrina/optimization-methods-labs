public class Fibonacci extends AbstractSolver implements Solver {
    private int iterationsNum;
    private double fibonacciN;

    public Fibonacci(double leftBound, double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        iterationsNum = countIterationsNum();
        calcMinX(false);
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
        double condition = (rightBound - leftBound) / EPSILON;
        while (fibonacciN - condition <= 0) {
            n++;
            fibonacciN = fibonacciNum(n);
        }
        this.fibonacciN = fibonacciN;
        return n;
    }

    private void calcMinX(boolean printSteps) {
        double a = leftBound, b = rightBound;
        double l = (b - a) / fibonacciN;
        double x1 = a + l * fibonacciNum(iterationsNum - 2);
        double x2 = a + l * fibonacciNum(iterationsNum - 1);
        double fX1 = calcFunc(x1);
        double fX2 = calcFunc(x2);
        int count = 0;
        for (int k = iterationsNum - 1; k > 1; k--) {
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
                x1 = a + (b - x2);
                fX1 = calcFunc(x1);
            } else {
                if (printSteps) {
                    System.out.print(String.format("%.10f", (b - a) / (b - x1)));
                    System.out.println();
                }
                a = x1;
                x1 = x2;
                fX1 = fX2;
                x2 = b - (x1 - a);
                fX2 = calcFunc(x2);
            }
        }
        minX = (x1 + x2) / 2;
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
