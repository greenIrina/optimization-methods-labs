public class GradientDescent extends AbstractSolver {
    public GradientDescent(double epsilon, QuadraticFunction quadraticFunction) {
        super(epsilon, quadraticFunction);
    }

    @Override
    protected Pair calcMin(Vector x, double xFunc, Vector gradient, double length) {
        while (length >= epsilon) {
            iterationsNumber++;
            Vector y = x.sum(gradient.multiplyByScalar(-alpha / length));
            double yFunc = quadraticFunction.apply(y);
            if (xFunc > yFunc) {
                x = new Vector(y);
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
