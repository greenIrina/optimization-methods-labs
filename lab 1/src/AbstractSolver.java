public abstract class AbstractSolver implements Solver {
    public double calcFunc(double x) {
        return -Math.pow(x, 0.2) + Math.pow(Math.log10(x - 2), 2) + Math.pow(Math.log10(10 - x), 2);
    }

    public void printValues(int count, double a, double b, double x1, double x2, double fX1, double fX2) {
        System.out.println("Step #" + count + ": a = " + a + ", b = " + b + ": x1 = " + x1 + ", x2 = " + x2
                + ", f(x1) = " + fX1 + ", f(x2) = " + fX2);
    }
}
