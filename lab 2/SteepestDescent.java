import oneDimensional.*;

import java.util.function.Function;

public class SteepestDescent extends AbstractSolver {

    public SteepestDescent(double epsilon, QuadraticFunction quadraticFunction) {
        super(epsilon, quadraticFunction);
    }

    @Override
    protected Pair calcMin(DoubleVector x, double xFunc, DoubleVector gradient, double length) {
        while (length >= epsilon) {
            iterationsNumber++;
            nextIter(x, gradient);
            x = x.sum(gradient.multiplyByScalar(-alpha));
            gradient = quadraticFunction.gradient(x);
            length = gradient.sqrtLength();
        }
        return new Pair(x, quadraticFunction.apply(x));
    }

    private void nextIter(DoubleVector x, DoubleVector gradient) {
        Function<Double, Double> functionAlpha = (Double alpha) -> quadraticFunction.apply(x.sum(gradient.multiplyByScalar(-alpha)));
        alpha = new GoldenSection(0, quadraticFunction.getL(), epsilon, functionAlpha).getMinX();
    }
}
