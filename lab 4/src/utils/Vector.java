package utils;

import java.util.ArrayList;
import java.util.List;

public class Vector {
    private final List<Double> coefficients;

    public Vector(List<Double> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    public Vector(double[] coefficients) {
        this.coefficients = new ArrayList<>();
        for (double el : coefficients) {
            this.coefficients.add(el);
        }
    }

    public Vector(Vector vector) {
        this(vector.getCoefficients());
    }

    public Vector() {
        this(new ArrayList<>());
    }

    public Vector multiplyByScalar(double coefficient) {
        Vector result = new Vector();
        for (Double aDouble : coefficients) {
            result.add(aDouble * coefficient);
        }
        return result;
    }

    public Vector sum(Vector vector) {
        Vector res = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            res.add(coefficients.get(i) + vector.get(i));
        }
        return res;
    }

    public double scalarMultiplication(Vector vector) {
        double res = 0;
        for (int i = 0; i < vector.size(); i++) {
            res += this.coefficients.get(i) * vector.get(i);
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

}
