import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final List<DoubleVector> matrix;

    public Matrix(List<List<Double>> a) {
        matrix = new ArrayList<>();
        for (List<Double> list : a) {
            matrix.add(new DoubleVector(list));
        }

    }

    public DoubleVector multiplyByVector(DoubleVector vector)  {
        DoubleVector res = new DoubleVector();
        for (DoubleVector vec : matrix) {
            res.add(vec.scalarMultiplication(vector));
        }
        return res;
    }

    public double get(int ind1, int ind2) {
        return matrix.get(ind1).get(ind2);
    }

    public DoubleVector get(int ind) {
        return matrix.get(ind);
    }

    public int size() {
        return matrix.size();
    }
}
