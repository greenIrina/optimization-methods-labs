import java.util.function.Function;

public class QuadraticFunction implements Function<Vector, Double> {

    private final Matrix a;
    private final Vector b;
    private final double c, l;
    private final int n;

    public QuadraticFunction(Matrix a, Vector b, double c, double l) {
        this.n = b.size();
        this.a = a;
        this.b = b;
        this.c = c;
        this.l = l;
    }

    @Override
    public Double apply(Vector vector) {
        return (vector.scalarMultiplication(a.multiplyByVector(vector))) / 2
                + vector.scalarMultiplication(b)
                + c;
    }

    public Vector gradient(Vector vector) {
        return a.multiplyByVector(vector).sum(b);
    }

    public int getN() {
        return n;
    }

    public Matrix getA() {
        return a;
    }

    public double getL() {
        return l;
    }
}
