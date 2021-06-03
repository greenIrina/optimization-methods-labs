package generators;

import matrices.ProfileMatrix;

import java.util.Arrays;
import java.util.Random;

public class TaskGenerator {
    public static TaskPair generateLUTask(int n, int k) {
        ProfileMatrix matrix = generateProfileMatrix(n, k);
        double[] x = new double[n];
        for (int i = 0; i < n; i++)
            x[i] = i + 1;

        return new TaskPair(matrix, matrix.multiplyByVector(x));
    }

    public static ProfileMatrix generateProfileMatrix(int n, int k) {
        double eps = Math.pow(10, -k);
        double[][] matrix = new double[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -random.nextInt(5);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 0;
            matrix[i][i] = -Arrays.stream(matrix[i], 0, n).sum();
            if (i == 0) {
                matrix[i][i] += eps;
            }
        }
        return new ProfileMatrix(n, matrix);
    }

    public static TaskPair generateHilbertTask(int n) {
        ProfileMatrix matrix = generateHilbertMatrix(n);
        double[] x = new double[n];
        for (int i = 0; i < n; i++)
            x[i] = i + 1;

        return new TaskPair(matrix, matrix.multiplyByVector(x));
    }

    public static ProfileMatrix generateHilbertMatrix(int n) {
        double[][] data = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = 1. / (i + j + 1);
            }
        }
        return new ProfileMatrix(n, data);
    }
}
