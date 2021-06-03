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

    public ProfileMatrix(ProfileMatrix profileMatrix) {
        n = profileMatrix.getN();
        diagonal = new ArrayList<>(profileMatrix.diagonal);
        al = new ArrayList<>(profileMatrix.al);
        au = new ArrayList<>(profileMatrix.au);
        ia = new ArrayList<>(profileMatrix.ia);
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
            return getUpperOrLowerElement(true, i, j);
        } else {
            return getUpperOrLowerElement(false, j, i);
        }
    }

    @Override
    public void setElement(int i, int j, double element) {
//        if (i != j &&
//                (i < j && profileLength(j) < j - i || j < i && profileLength(i) < i - j)
//                || (element == 0 && getElement(j, i) != 0)) {
//            throw new UnsupportedOperationException("Can't change profile");
//        }
        if (i == j) {
            diagonal.set(i, element);
        } else if (j < i) {
            al.set(indexInTriangle(j, i), element);
        } else {
            au.set(indexInTriangle(i, j), element);
        }
    }

    @Override
    public void swapRows(int row1, int row2) {
        throw new UnsupportedOperationException("Can't change profile");
    }

    private int profileLength(int i) {
        return ia.get(i + 1) - ia.get(i);
    }


    private double getUpperOrLowerElement(boolean isLower, int i, int j) {
        int len = profileLength(i);
        if (j < i - len) {
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
