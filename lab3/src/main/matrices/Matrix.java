package matrices;

public interface Matrix {
    int getN();

    double getElement(int i, int j);

    void setElement(int i, int j, double element);

    void swapRows(int row1, int row2);

    default double[] multiplyByVector(final double[] vector) {
        final double[] answer = new double[vector.length];
        for (int row = 0; row < getN(); row++) {
            for (int col = 0; col < getN(); col++) {
                answer[row] += vector[col] * getElement(row, col);
            }
        }
        return answer;
    }
}
