public class GoldenFibonacciImpl extends AbstractSolver {
    private double a, b, x1, x2, fX1, fX2;
    private int count;

    public GoldenFibonacciImpl(double a, double b, double x1, double x2, double fX1, double fX2, int count,
                               Logger logger) {
        this.a = a;
        this.b = b;
        this.x1 = x1;
        this.x2 = x2;
        this.fX1 = fX1;
        this.fX2 = fX2;
        this.count = count;
        this.logger = logger;
    }

    private double calcX1(boolean goldenSection) {
        if (goldenSection) {
            return b - TAU * (b - a);
        }
        return a + (b - x2);
    }

    private double calcX2(boolean goldenSection) {
        if (goldenSection) {
            return a + TAU * (b - a);
        }
        return b - (x1 - a);
    }

    public void calcMinImpl(boolean goldenSection) {
        count++;
        logger.writeData(values(count, a, b, x1, x2, fX1, fX2), count);
        if (fX1 - fX2 <= 0) {
            b = x2;
            x2 = x1;
            fX2 = fX1;
            x1 = calcX1(goldenSection);
            fX1 = calcFunc(x1);
        } else {
            a = x1;
            x1 = x2;
            fX1 = fX2;
            x2 = calcX2(goldenSection);
            fX2 = calcFunc(x2);
        }
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }
}
