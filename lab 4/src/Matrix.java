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

    public Vector multiplyByVector(Vector vector)  {
        Vector res = new Vector();
        for (double[] vec : matrix) {
            res.add(vector.scalarMultiplication(new Vector(vec)));
        }
        return res;
    }
}
