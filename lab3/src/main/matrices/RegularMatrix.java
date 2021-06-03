package matrices;

public class RegularMatrix implements Matrix {
    private final int n;
    private final double[][] matrix;

    public RegularMatrix(int n, double[][] matrix) {
        this.n = n;
        this.matrix = matrix;
    }

    public RegularMatrix(Matrix matrix) {
        this.n = matrix.getN();
        this.matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matrix[i][j] = matrix.getElement(i, j);
            }
        }
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public double getElement(int i, int j) {
        return matrix[i][j];
    }

    @Override
    public void setElement(int i, int j, double el) {
        matrix[i][j] = el;
    }

    public void swapRows(int row1, int row2) {
        double[] tmp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = tmp;
    }
}
