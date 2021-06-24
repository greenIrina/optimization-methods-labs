package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DoubleVector {
    private final List<Double> coefficients;

    public DoubleVector(List<Double> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    public DoubleVector(double[] coefficients) {
        this.coefficients = new ArrayList<>();
        for (double el : coefficients) {
            this.coefficients.add(el);
        }
    }

    public DoubleVector(DoubleVector vector) {
        this(vector.getCoefficients());
    }

    public DoubleVector() {
        this(new ArrayList<>());
    }

    public DoubleVector multiplyByScalar(double coefficient) {
        DoubleVector result = new DoubleVector();
        for (Double aDouble : coefficients) {
            result.add(aDouble * coefficient);
        }
        return result;
    }

    public DoubleVector sum(DoubleVector vector) {
        DoubleVector res = new DoubleVector();
        for (int i = 0; i < vector.size(); i++) {
            res.add(coefficients.get(i) + vector.get(i));
        }
        return res;
    }

    public double scalarMultiplication(DoubleVector vector) {
        double res = 0;
        for (int i = 0; i < vector.size(); i++) {
            res += this.coefficients.get(i) * vector.get(i);
        }
        return res;
    }

    public double[] toArray() {
        double[] res = new double[size()];
        for (int i = 0; i < size(); i++) {
            res[i] = coefficients.get(i);
        }
        return res;
    }

    public int size() {
        return coefficients.size();
    }

    public double length() {
        double length = 0;
        for (double c : coefficients) {
            length += c * c;
        }
        return length;
    }

    public double sqrtLength() {
        return Math.sqrt(length());
    }

    public void set(int ind, double el) {
        coefficients.set(ind, el);
    }

    public double get(int index) {
        return coefficients.get(index);
    }

    public void add(double element) {
        coefficients.add(element);
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    public Matrix multiplyByTransposed() {
        int n = size();
        double[][] data = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] += get(i) * get(j);
            }
        }
        return new Matrix(data);
    }

    public Matrix multiplyByTransposed(DoubleVector vector) {
        int n = size();
        double[][] data = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] += get(i) * vector.get(j);
            }
        }
        return new Matrix(data);
    }

}
