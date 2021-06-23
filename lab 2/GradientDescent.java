public class GradientDescent extends AbstractSolver {
    public GradientDescent(double epsilon, QuadraticFunction quadraticFunction) {
        super(epsilon, quadraticFunction);
    }

    @Override
    protected Pair calcMin(DoubleVector x, double xFunc, DoubleVector gradient, double length) {
        while (length >= epsilon) {
            iterationsNumber++;
            DoubleVector y = x.sum(gradient.multiplyByScalar(-alpha / length));
            double yFunc = quadraticFunction.apply(y);
            if (xFunc > yFunc) {
                x = new DoubleVector(y);
                xFunc = yFunc;
                gradient = quadraticFunction.gradient(x);
                length = gradient.sqrtLength();
            } else {
                alpha /= 2;
            }
        }

        return new Pair(x, xFunc);
    }
}
