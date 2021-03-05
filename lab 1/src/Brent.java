import java.util.List;

public class Brent extends AbstractSolver implements Solver {
    public Brent(double leftBound, double rightBound, double epsilon) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        createLogger("B, eps=" + epsilon, false);
        calcMinX();
    }

    private void calcMinX() {
        double a = leftBound, c = rightBound;
        double x = a + K * (c - a), w = x, v = x;
        double fX = calcFunc(x), fW = fX, fV = fX;
        double d = c - a, e = d;
        List<Double> values;
        double u = 0, fU = 0;
        boolean parabolaU = false;
        while (true) {
            double g = e;
            e = d;
            double tol = epsilon * Math.abs(x) + epsilon / 10;
            if (Math.abs(x - (a + c) / 2) + (c - a) / 2 - 2 * tol <= 0) {
                minX = x;
                minFunc = fX;
                break;
            }
            if (Math.abs(x - w) >= epsilon && Math.abs(x - v) >= epsilon && Math.abs(v - w) >= epsilon
                    && Math.abs(fX - fW) >= epsilon && Math.abs(fX - fV) >= epsilon && Math.abs(fV - fW) >= epsilon) {
                values = calcParabolaMin(x, w, v, fX, fW, fV);
                u = values.get(2);
                if (u - a >= 0 && c - u >= 0 && Math.abs(u - x) - g / 2 < 0) {
                    parabolaU = true;
                    if (u - a - 2 * tol < 0 || c - u - 2 * tol < 0) {
                        u = x - Math.signum(x - (a + c) / 2) * tol;
                    }
                } else {
                    parabolaU = false;
                }
            } else if (!parabolaU) {
                if (x - (a + c) / 2 < 0) {
                    u = x + K * (c - x);
                    e = c - x;
                } else {
                    u = x - K * (x - a);
                    e = x - a;
                }
            }
            if (Math.abs(u - x) - tol < 0) {
                u = x + Math.signum(u - x) * tol;
            }
            d = Math.abs(u - x);
            fU = calcFunc(u);
            if (fU - fX <= 0) {
                if (u - x >= 0) {
                    a = x;
                } else {
                    c = x;
                }
                v = w;
                w = x;
                x = u;
                fV = fW;
                fW = fX;
                fX = fU;
            } else {
                if (u - x >= 0) {
                    c = u;
                } else {
                    a = u;
                }
                if (fU - fW <= 0 || w == x) {
                    v = w;
                    w = u;
                    fV = fW;
                    fW = fU;
                } else if (fU - fV <= 0 || v == x || v == w) {
                    v = u;
                    fV = fU;
                }
            }
        }
    }
}
