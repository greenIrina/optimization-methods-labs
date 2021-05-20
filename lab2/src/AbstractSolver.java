public abstract class AbstractSolver implements Solver {
    protected double epsilon, alpha;
    protected QuadraticFunction quadraticFunction;
    protected long iterationsNumber;

    public AbstractSolver(double epsilon, QuadraticFunction quadraticFunction) {
        this.epsilon = epsilon;
        this.quadraticFunction = quadraticFunction;
        this.alpha = 100;
        iterationsNumber = 0;
    }

    public Pair findMin(Vector startVector) {
        Vector x = new Vector(startVector);
        double xFunc = quadraticFunction.apply(x);
        Vector gradient = quadraticFunction.gradient(x);
        double length = gradient.sqrtLength();
        if (length < epsilon) {
            return new Pair(x, xFunc);
        }
        iterationsNumber = 0;
        return calcMin(x, xFunc, gradient, length);
    }

    protected Pair calcMin(Vector x, double xFunc, Vector gradient, double length) {
        return new Pair(x, xFunc);
    }

    public long getIterationsNumber() {
        return iterationsNumber;
    }
}
