import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private final List<Vector> matrix;

    public Matrix(List<List<Double>> a) {
        matrix = new ArrayList<>();
        for (List<Double> list : a) {
            matrix.add(new Vector(list));
        }

    }

    public Vector multiplyByVector(Vector vector)  {
        Vector res = new Vector();
        for (Vector vec : matrix) {
            res.add(vec.scalarMultiplication(vector));
        }
        return res;
    }

    public double get(int ind1, int ind2) {
        return matrix.get(ind1).get(ind2);
    }

    public Vector get(int ind) {
        return matrix.get(ind);
    }

    public int size() {
        return matrix.size();
    }
}
