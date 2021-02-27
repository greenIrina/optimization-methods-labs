public abstract class AbstractSolver implements Solver {
    public double calcFunc(double x) {
        return -Math.pow(x, 0.2) + Math.pow(Math.log10(x - 2), 2) + Math.pow(Math.log10(10 - x), 2);
    }

    public void printValues(int count, double a, double b, double x1, double x2, double fX1, double fX2) {
        System.out.print("Step #" + count + ": a = " + formatDouble(a) + ", b = " + formatDouble(b) + ": x1 = "
                + formatDouble(x1) + ", x2 = " + formatDouble(x2) + ", f(x1) = " + formatDouble(fX1)
                + ", f(x2) = " + formatDouble(fX2) + ", отношение длин интервалов: ");
    }

    private String formatDouble(double d) {
        return String.format("%.10f", d);
    }
}
