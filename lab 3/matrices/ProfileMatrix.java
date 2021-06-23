package matrices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileMatrix implements Matrix {
    private final List<Double> diagonal;
    private final List<Double> al;
    private final List<Double> au;
    private final List<Integer> ia;
    private final int n;

    public ProfileMatrix(int n, List<Double> diagonal, List<Double> al, List<Double> au, List<Integer> ia) {
        this.n = n;
        this.diagonal = diagonal;
        this.al = al;
        this.au = au;
        this.ia = ia;
    }

    public ProfileMatrix(int n, double[][] matrix) {
        this(n, new RegularMatrix(n, matrix));
    }

    private ProfileMatrix(int n, Matrix matrix) {
        this.n = n;
        diagonal = new ArrayList<>();
        al = new ArrayList<>();
        au = new ArrayList<>();
        ia = new ArrayList<>(Collections.nCopies(n + 1, 0));
        ia.set(0, 1);
        ia.set(1, 1);
        for (int i = 0; i < n; i++) {
            diagonal.add(matrix.getElement(i, i));
        }

        int counter = 0;
        for (int row = 1; row < n; row++) {
            boolean wasNotZero = false;
            for (int j = 0; j < row; j++) {
                double element = matrix.getElement(row, j);
                if (element != 0) {
                    wasNotZero = true;
                }
                if (wasNotZero) {
                    counter++;
                    al.add(element);
                    au.add(matrix.getElement(j, row));
                }
            }
            ia.set(row + 1, counter + ia.get(row));
            counter = 0;
        }
    }

    @Override
    public int getN() {
        return n;
    }

    public List<Double> getDiagonal() {
        return diagonal;
    }

    public List<Double> getAl() {
        return al;
    }

    public List<Double> getAu() {
        return au;
    }

    public List<Integer> getIa() {
        return ia;
    }

    @Override
    public double getElement(int i, int j) {
        if (i == j) {
            return diagonal.get(i);
        }
        if (j < i) {
            return getUpperOrLowerElement(i, j, true);
        } else {
            return getUpperOrLowerElement(j, i, false);
        }
    }

    @Override
    public void setElement(int i, int j, double element) {
        if (i == j) {
            diagonal.set(i, element);
        } else if (j < i) {
            setUpperOrLower(i, j, element, true);
        } else {
            setUpperOrLower(j, i, element, false);
        }
    }

    @Override
    public void swapRows(int row1, int row2) {
        throw new UnsupportedOperationException("Can't change profile");
    }

    private int profileLength(int i) {
        return ia.get(i + 1) - ia.get(i);
    }

    private void setUpperOrLower(int i, int j, double element, boolean isLower) {
        if (j < i - profileLength(i)) {
            return;
        }
        if (isLower) {
            al.set(indexInTriangle(i, j), element);
        } else {
            au.set(indexInTriangle(i, j), element);
        }
    }


    private double getUpperOrLowerElement(int i, int j, boolean isLower) {
        if (j < i - profileLength(i)) {
            return 0.;
        }
        if (isLower) {
            return al.get(indexInTriangle(i, j));
        } else {
            return au.get(indexInTriangle(i, j));
        }
    }

    private int indexInTriangle(int i, int j) {
        return ia.get(i) - 1 + j - i + profileLength(i);
    }
}
