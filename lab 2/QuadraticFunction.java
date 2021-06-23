import java.util.function.Function;

public class QuadraticFunction implements Function<DoubleVector, Double> {

    private final Matrix a;
    private final DoubleVector b;
    private final double c, l;
    private final int n;

    public QuadraticFunction(Matrix a, DoubleVector b, double c, double l) {
        this.n = b.size();
        this.a = a;
        this.b = b;
        this.c = c;
        this.l = l;
    }

    @Override
    public Double apply(DoubleVector vector) {
        return (vector.scalarMultiplication(a.multiplyByVector(vector))) / 2
                + vector.scalarMultiplication(b)
                + c;
    }

    public DoubleVector gradient(DoubleVector vector) {
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
