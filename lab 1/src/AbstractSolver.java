import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSolver implements Solver {
    protected double leftBound, rightBound, minX, minFunc, epsilon;
    protected Logger logger;
    protected static final double TAU_1 = (Math.sqrt(5) - 1) / 2;
    protected static final double TAU_2 = (3 - Math.sqrt(5)) / 2;

    public double calcFunc(double x) {
        return -Math.pow(x, 0.2) + Math.pow(Math.log10(x - 2), 2) + Math.pow(Math.log10(10 - x), 2);
    }

    protected List<String> headers(int type) {
        if (type == 0) {
            return Arrays.asList("Step", "a", "b", "x1", "x2", "f(x1)", "f(x2)", "отношение длин интервалов");
        } else if (type == 1) {
            return Arrays.asList("Step", "a1", "a2", "x1", "x2", "x3", "f(x1)", "f(x2)", "f(x3)", "x_med", "f(x_med)",
                    "отношение длин интервалов");
        } else if (type == 2) {
            return Arrays.asList("Step", "a", "c", "x", "w", "v", "f(x)", "f(w)", "f(v)", "u", "f(u)",
                    "отношение длин интервалов", "");
        }
        return Collections.emptyList();
    }

    protected List<Double> values(double count, double a, double b, double x1, double x2, double fX1, double fX2) {
        return Arrays.asList(count, a, b, x1, x2, fX1, fX2);
    }

    protected List<Double> values(double count, double a1, double a2, double x1, double x2, double x3, double fX1,
                                  double fX2, double fX3, double xMed, double fXMed) {
        return Arrays.asList(count, a1, a2, x1, x2, x3, fX1, fX2, fX3, xMed, fXMed);
    }

    protected List<Double> values(double count, double a, double c, double x, double w, double v, double fX,
                                  double fW, double fV, double u, double fU, double lengths) {
        return Arrays.asList(count, a, c, x, w, v, fX, fW, fV, u, fU, lengths);
    }

    protected void createLogger(String method, int type) {
        logger = new Logger(method);
        logger.createHeaders(headers(type));
        logger.writeEpsilon(epsilon);
    }

    protected List<Double> calcParabolaMin(double x1, double x2, double x3, double fX1, double fX2, double fX3) {
        double a1, a2, xMed, fXMed;
        a1 = (fX2 - fX1) / (x2 - x1);
        a2 = ((fX3 - fX1) / (x3 - x1) - (fX2 - fX1) / (x2 - x1)) / (x3 - x2);
        xMed = (x1 + x2 - a1 / a2) / 2;
        fXMed = calcFunc(xMed);
        return Arrays.asList(a1, a2, xMed, fXMed);
    }

    public double getMinX() {
        return minX;
    }

    public double getMinFunc() {
        return minFunc;
    }
}
