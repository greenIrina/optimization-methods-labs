package solvers;

import matrices.Matrix;

public class LUSolver implements Solver {
    private final double[] x;

    public LUSolver(Matrix matrix, double[] b) {
        int n = matrix.getN();
        matrix.setElement(0, 0, matrix.getElement(0, 0));
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                double lij = countLUElement(i, j, j, matrix.getElement(i, j), matrix);
                matrix.setElement(i, j, lij);
            }
            for (int j = 0; j < i; j++) {
                double uji = (countLUElement(j, i, j, matrix.getElement(j, i), matrix)) / matrix.getElement(j, j);
                matrix.setElement(j, i, uji);
            }
            double lii = countLUElement(i, i, i, matrix.getElement(i, i), matrix);
            matrix.setElement(i, i, lii);
        }
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = b[i];
            for (int j = 0; j < i; j++) {
                sum -= y[j] * matrix.getElement(i, j);
            }
            y[i] = sum / matrix.getElement(i, i);
        }
        x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = y[i];
            for (int j = i + 1; j < n; j++) {
                sum -= matrix.getElement(i, j) * x[j];
            }
            x[i] = sum;
        }
    }

    private double countLUElement(int i, int j, int ind, double start, Matrix matrix) {
        double sum = start;
        for (int k = 0; k < ind; k++) {
            sum -= matrix.getElement(i, k) * matrix.getElement(k, j);
        }
        return sum;
    }

    @Override
    public double[] solve() {
        return x;
    }
}
