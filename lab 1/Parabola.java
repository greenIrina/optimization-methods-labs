import java.util.List;
import java.util.function.Function;

public class Parabola extends AbstractSolver implements Solver {
    public Parabola(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        this.function = function;
        createLogger("P, eps=" + epsilon, 1);
        calcMin();
    }

    private void calcMin() {
        double x1 = leftBound, x2 = (leftBound + rightBound) / 2, x3 = rightBound;
        double fX1 = function.apply(x1), fX2 = function.apply(x2), fX3 = function.apply(x3);
        int k = 0, cnt = 3;

        List<Double> values = calcParabolaMin(x1, x2, x3, fX1, fX2, fX3);
        double xMed = values.get(2);
        double fXMed = values.get(3);
        cnt++;

        logger.writeData(values(k, values.get(0), values.get(1), x1, x2, x3, fX1, fX2, fX3, xMed, fXMed), k + 1);

        double xPrev = xMed;

        while (true) {
            if (k > 0) {
                values = calcParabolaMin(x1, x2, x3, fX1, fX2, fX3);
                cnt++;
                xMed = values.get(2);
                fXMed = values.get(3);
                logger.writeData(values(k, values.get(0), values.get(1), x1, x2, x3, fX1, fX2, fX3, xMed, fXMed), k + 1);

                if (Math.abs(xMed - xPrev) <= epsilon) {
                    minX = xMed;
                    minFunc = fXMed;
                    break;
                }
            }
            if (x1 < xMed && xMed < x2) {
                if (fXMed >= fX2) {
                    x1 = xMed;
                    fX1 = fXMed;
                } else {
                    x3 = x2;
                    fX3 = fX2;
                    x2 = xMed;
                    fX2 = fXMed;
                }
            } else if (x2 < xMed && xMed < x3) {
                if (fX2 >= fXMed) {
                    x1 = x2;
                    fX1 = fX2;
                    x2 = xMed;
                    fX2 = fXMed;
                } else {
                    x3 = xMed;
                    fX3 = fXMed;
                }
            }
            k++;
            xPrev = xMed;
        }
        logger.writeCntFunc(cnt);
        logger.writeInFile();
    }
}
