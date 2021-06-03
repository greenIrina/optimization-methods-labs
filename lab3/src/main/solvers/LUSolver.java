package solvers;

import matrices.Matrix;
import matrices.RegularMatrix;

public class LUSolver implements Solver {
    private Matrix componentsLU;
    private final int n;

    public LUSolver(int n, Matrix matrix) {
        this.n = n;
        decomposeMatrix(matrix);
    }

    private void decomposeMatrix(Matrix matrix) {
        componentsLU = new RegularMatrix(n, new double[n][n]);
        componentsLU.setElement(0, 0, matrix.getElement(0, 0));
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                double lij = countLUElement(i, j, j, matrix.getElement(i, j));
                componentsLU.setElement(i, j, lij);
            }
            for (int j = 0; j < i; j++) {
                double uji = (countLUElement(j, i, j, matrix.getElement(j, i))) / componentsLU.getElement(j, j);
                componentsLU.setElement(j, i, uji);
            }
            double lii = countLUElement(i, i, i, matrix.getElement(i, i));
            componentsLU.setElement(i, i, lii);
        }
    }

    private double countLUElement(int i, int j, int ind, double start) {
        double sum = start;
        for (int k = 0; k < ind; k++) {
            sum -= componentsLU.getElement(i, k) * componentsLU.getElement(k, j);
        }
        return sum;
    }

    public Matrix getL() {
        RegularMatrix result = new RegularMatrix(n, new double[n][n]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result.setElement(i, j, componentsLU.getElement(i, j));
            }
        }
        return result;
    }

    public Matrix getU() {
        RegularMatrix result = new RegularMatrix(n, new double[n][n]);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    result.setElement(i, j, 1);
                } else {
                    result.setElement(i, j, componentsLU.getElement(i, j));
                }
            }
        }
        return result;
    }

    @Override
    public double[] solve(double[] b) {
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = b[i];
            for (int j = 0; j < i; j++) {
                sum -= y[j] * componentsLU.getElement(i, j);
            }
            y[i] = sum / componentsLU.getElement(i, i);
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = y[i];
            for (int j = i + 1; j < n; j++) {
                sum -= componentsLU.getElement(i, j) * x[j];
            }
            x[i] = sum;
        }
        return x;
    }
}
