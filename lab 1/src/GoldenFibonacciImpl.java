public class GoldenFibonacciImpl extends AbstractSolver {
    private double a, b, x1, x2, fX1, fX2, tau;
    private int count;
    private boolean printSteps;

    public GoldenFibonacciImpl(double a, double b, double x1, double x2, double fX1, double fX2, double tau,
                               int count, boolean printSteps) {
        this.a = a;
        this.b = b;
        this.x1 = x1;
        this.x2 = x2;
        this.fX1 = fX1;
        this.fX2 = fX2;
        this.tau = tau;
        this.count = count;
        this.printSteps = printSteps;
    }

    private double calcX1(boolean goldenSection) {
        if (goldenSection) {
            return b - tau * (b - a);
        }
        return a + (b - x2);
    }

    private double calcX2(boolean goldenSection) {
        if (goldenSection) {
            return a + tau * (b - a);
        }
        return b - (x1 - a);
    }

    public void calcMinImpl(boolean goldenSection) {
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
            x1 = calcX1(goldenSection);
            fX1 = calcFunc(x1);
        } else {
            if (printSteps) {
                System.out.print(String.format("%.10f", (b - a) / (b - x1)));
                System.out.println();
            }
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

    @Override
    public double getMinX() {
        return 0;
    }

    @Override
    public double getMinFunc() {
        return 0;
    }

    @Override
    public void printSteps() {

    }
}
