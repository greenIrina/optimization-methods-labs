import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FunctionGenerator {
    private final QuadraticFunction quadraticFunction;

    public FunctionGenerator(double a1, double a2, double a3, double b1, double b2, double c, double l) {
        List<List<Double>> A = new ArrayList<>(Collections.nCopies(2, new ArrayList<>()));
        List<Double> tmp = new ArrayList<>();
        tmp.add(a1 * 2);
        tmp.add(a2);
        A.set(0, tmp);
        tmp = new ArrayList<>();
        tmp.add(a2);
        tmp.add(a3 * 2);
        A.set(1, tmp);
        List<Double> B = new ArrayList<>();
        B.add(b1);
        B.add(b2);
        Matrix a = new Matrix(A);
        DoubleVector b = new DoubleVector(B);
        quadraticFunction = new QuadraticFunction(a, b, c, l);
    }

    public FunctionGenerator(int n, int k) {
        List<List<Double>> a = new ArrayList<>(Collections.nCopies(n, new ArrayList<>()));
        List<Double> tmp = new ArrayList<>(Collections.nCopies(n, 0.));
        tmp.set(0, 1.);
        a.set(0, tmp);
        double prev = 1.0, step = k == 1 ? 0 : k * 1.0 / n;
        for (int i = 1; i < n - 1; i++) {
            tmp = new ArrayList<>(Collections.nCopies(n, 0.));
            tmp.set(i, prev + step);
            prev += step;
            a.set(i, tmp);
        }
        tmp = new ArrayList<>(Collections.nCopies(n, 0.));
        tmp.set(n - 1, k * 1.);
        a.set(n - 1, tmp);
        quadraticFunction = new QuadraticFunction(new Matrix(a), new DoubleVector(new ArrayList<>(Collections.nCopies(n, 0.))), 0, k);

    }

    public QuadraticFunction getQuadraticFunction() {
        return quadraticFunction;
    }
}
