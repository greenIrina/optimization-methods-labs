package oneDimensional;

import java.util.List;
import java.util.function.Function;

public class Brent extends OneDimensionalSolver {
    public Brent(double leftBound, double rightBound, double epsilon, Function<Double, Double> function) {
        super(leftBound, rightBound, epsilon, function);
        calcMinX();
    }

    private void calcMinX() {
        double a = leftBound, c = rightBound;
        double x = a + TAU_2 * (c - a), w = x, v = x;
        double fX = function.apply(x), fW = fX, fV = fX;
        double d = c - a, e = d;
        List<Double> values;
        double u = x, fU = fX;
        boolean parabolaU;
        while (d > epsilon) {
            parabolaU = false;
            double g = e;
            e = d;
            double tol = epsilon * Math.abs(x) + epsilon / 10;
            if (Math.abs(x - (a + c) / 2) + (c - a) / 2 - 2 * tol <= 0) {
                break;
            }

            if (!(x == w || x == v || w == v || fX == fW || fX == fV || fV == fW)) {
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
            }
            if (!parabolaU) {
                if (x - (a + c) / 2 < 0) {
                    u = x + TAU_2 * (c - x);
                    e = c - x;
                } else {
                    u = x - TAU_2 * (x - a);
                    e = x - a;
                }
            }
            if (Math.abs(u - x) - tol < 0) {
                u = x + Math.signum(u - x) * tol;
            }
            d = Math.abs(u - x);
            fU = function.apply(u);
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
        minX = x;
        minFunc = fX;
    }
}
