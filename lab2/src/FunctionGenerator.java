import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FunctionGenerator {
    private final QuadraticFunction quadraticFunction;

    public FunctionGenerator(double a11, double a12, double a21, double a22, double b1, double b2, double c, double l) {
        List<List<Double>> A = new ArrayList<>(Collections.nCopies(2, new ArrayList<>()));
        List<Double> tmp = new ArrayList<>();
        tmp.add(a11);
        tmp.add(a12);
        A.set(0, tmp);
        tmp = new ArrayList<>();
        tmp.add(a21);
        tmp.add(a22);
        A.set(1, tmp);
        List<Double> B = new ArrayList<>();
        B.add(b1);
        B.add(b2);
        Matrix a = new Matrix(A);
        Vector b = new Vector(B);
        quadraticFunction = new QuadraticFunction(a, b, c, l);
    }

    public FunctionGenerator(int n, int k) {
        List<List<Double>> a = new ArrayList<>(Collections.nCopies(n, new ArrayList<>()));
        Random random = new Random();
        List<Double> tmp = new ArrayList<>(Collections.nCopies(n, 0.));
        tmp.set(0, 1.);
        a.set(0, tmp);
        for (int i = 1; i < n - 1; i++) {
            tmp = new ArrayList<>(Collections.nCopies(n, 0.));
            double el = Math.abs(random.nextDouble() - k);
            tmp.set(i, el);
            a.set(i, tmp);
        }
        tmp = new ArrayList<>(Collections.nCopies(n, 0.));
        tmp.set(n - 1, k * 1.);
        a.set(n - 1, tmp);
        quadraticFunction = new QuadraticFunction(new Matrix(a), new Vector(new ArrayList<>(Collections.nCopies(n, 0.))), 0, k);

    }

    public QuadraticFunction getQuadraticFunction() {
        return quadraticFunction;
    }
}
