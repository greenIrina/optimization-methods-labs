package methods;

import utils.Matrix;
import utils.DoubleVector;

public class GaussSolver {
    private final double[] x;

    public GaussSolver(Matrix matrix, DoubleVector b, double eps) {
        int n = matrix.getN();
        for (int k = 0; k < n - 1; k++) {
            double maxCoefficient = -Double.MAX_VALUE;
            int maxCoefficientInd = k;
            for (int i = k; i < n; i++) {
                double element = matrix.getElement(i, k);
                if (Math.abs(element) - Math.abs(maxCoefficient) > eps) {
                    maxCoefficient = element;
                    maxCoefficientInd = i;
                }
            }
            matrix.swapRows(k, maxCoefficientInd);
            double tmp = b.get(k);
            b.set(k, b.get(maxCoefficientInd));
            b.set(maxCoefficientInd, tmp);
            final double kk = matrix.getElement(k, k);
//            if (Math.abs(kk) < eps) {
//                throw new IllegalStateException("Zero or infinity solutions");
//            }
            for (int i = k + 1; i < n; i++) {
                final double ik = matrix.getElement(i, k);
                for (int j = k; j < n; j++) {
                    matrix.setElement(i, j, matrix.getElement(i, j) - ik * matrix.getElement(k, j) / kk);
                }
                b.set(i, b.get(i) - ik * b.get(k) / kk);
            }
        }
        x = new double[n];
        for (int k = n - 1; k >= 0; k--) {
            double sum = b.get(k);
            for (int j = k + 1; j < n; j++) {
                sum -= matrix.getElement(k, j) * x[j];
            }
            x[k] = sum / matrix.getElement(k, k);
        }
    }


    public DoubleVector solve() {
        return new DoubleVector(x);
    }
}