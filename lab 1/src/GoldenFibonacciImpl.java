import java.util.function.Function;

public class GoldenFibonacciImpl extends AbstractSolver {
    private double a, b, x1, x2, fX1, fX2;
    private int count, cnt;

    public GoldenFibonacciImpl(double a, double b, double x1, double x2, double fX1, double fX2, int count, int cnt,
                               Logger logger, Function<Double, Double> function) {
        this.a = a;
        this.b = b;
        this.x1 = x1;
        this.x2 = x2;
        this.fX1 = fX1;
        this.fX2 = fX2;
        this.count = count;
        this.cnt = cnt;
        this.logger = logger;
        this.function = function;
    }

    private double calcX1(boolean goldenSection) {
        if (goldenSection) {
            return b - TAU_1 * (b - a);
        }
        return a + (b - x2);
    }

    private double calcX2(boolean goldenSection) {
        if (goldenSection) {
            return a + TAU_1 * (b - a);
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
            fX1 = function.apply(x1);
            cnt++;
        } else {
            a = x1;
            x1 = x2;
            fX1 = fX2;
            x2 = calcX2(goldenSection);
            fX2 = function.apply(x2);
            cnt++;
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

    public int getCnt() {
        return cnt;
    }
}
