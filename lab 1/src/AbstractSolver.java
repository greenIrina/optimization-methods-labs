import java.util.Arrays;
import java.util.List;

public abstract class AbstractSolver implements Solver {
    protected double leftBound, rightBound, minX, minFunc, epsilon;
    protected Logger logger;

    public double calcFunc(double x) {
        return -Math.pow(x, 0.2) + Math.pow(Math.log10(x - 2), 2) + Math.pow(Math.log10(10 - x), 2);
    }

    public List<String> headers(boolean isParabolaMethod) {
        if (isParabolaMethod) {
            return Arrays.asList("Step", "a1", "a2", "x1", "x2", "x3", "f(x1)", "f(x2)", "f(x3)", "x_med", "f(x_med)",
                    "отношение длин интервалов", "точность epsilon = " + epsilon);
        }
        return Arrays.asList("Step", "a", "b", "x1", "x2", "f(x1)", "f(x2)", "отношение длин интервалов",
                "точность epsilon = " + epsilon);
    }

    public List<Double> values(double count, double a, double b, double x1, double x2, double fX1, double fX2) {
        return Arrays.asList(count, a, b, x1, x2, fX1, fX2);
    }

    public List<Double> values(double count, double a1, double a2, double x1, double x2, double x3, double fX1,
                               double fX2, double fX3, double xMed, double fXMed) {
        return Arrays.asList(count, a1, a2, x1, x2, x3, fX1, fX2, fX3, xMed, fXMed);
    }

    protected void createLogger(String method, boolean isParabolaMethod) {
        logger = new Logger(method);
        logger.createHeaders(headers(isParabolaMethod));
    }
}
