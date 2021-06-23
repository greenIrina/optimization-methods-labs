package utils;

public class Matrix {
    private final int n;
    private final double[][] matrix;

    public Matrix(double[][] matrix) {
        this.n = matrix.length;
        this.matrix = matrix;
    }

    public Matrix(Matrix matrix) {
        this.n = matrix.getN();
        this.matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matrix[i][j] = matrix.getElement(i, j);
            }
        }
    }

    public static Matrix unary(int n) {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1.;
            }
        }
        return new Matrix(matrix);
    }

    public int getN() {
        return n;
    }

    public double getElement(int i, int j) {
        return matrix[i][j];
    }

    public void setElement(int i, int j, double el) {
        matrix[i][j] = el;
    }

    public void swapRows(int row1, int row2) {
        double[] tmp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = tmp;
    }

    public Matrix multiplyByMatrix(Matrix matrix) {
        double[][] res = new double[n][];
        for (int i = 0; i < res.length; i++) {
            res[i] = multiplyByVector(matrix.getColumn(i)).toArray();
        }
        return new Matrix(res).transposed();
    }

    protected DoubleVector getColumn(int ind) {
        DoubleVector vector = new DoubleVector();
        for (int i = 0; i < n; i++) {
            vector.add(matrix[i][ind]);
        }
        return vector;
    }

    public Matrix transposed() {
        double[][] res = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j]=matrix[j][i];
            }
        }
        return new Matrix(res);
    }

    public DoubleVector multiplyByVector(DoubleVector vector) {
        DoubleVector res = new DoubleVector();
        for (double[] vec : matrix) {
            res.add(vector.scalarMultiplication(new DoubleVector(vec)));
        }
        return res;
    }

    public Matrix multiplyByScalar(double scalar) {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = getElement(i, j) * scalar;
            }
        }
        return new Matrix(matrix);
    }

    public Matrix sum(Matrix matrix) {
        double[][] data = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = matrix.getElement(i, j) + getElement(i, j);
            }
        }
        return new Matrix(data);
    }
}
