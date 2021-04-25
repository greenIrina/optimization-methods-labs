public class ConjugateGradientMethod extends AbstractSolver {

    public ConjugateGradientMethod(double epsilon, QuadraticFunction quadraticFunction) {
        super(epsilon, quadraticFunction);
    }

    @Override
    protected Pair calcMin(Vector xK, double xFunc, Vector gradient, double length) {
        Vector x;
        Vector nextGradient = quadraticFunction.gradient(xK);
        Vector p = nextGradient.multiplyByScalar(-1);
        while (length >= epsilon) {
            iterationsNumber++;
            x = new Vector(xK);
            gradient = new Vector(nextGradient);
            Vector aP = quadraticFunction.getA().multiplyByVector(p);
            length = gradient.scalarMultiplication(gradient);
            double lambda = length / aP.scalarMultiplication(p);
            xK = x.sum(p.multiplyByScalar(lambda));
            nextGradient = gradient.sum(aP.multiplyByScalar(lambda));
            if (iterationsNumber % quadraticFunction.getN() != 0) {
                double beta = nextGradient.scalarMultiplication(nextGradient) / length;
                p = quadraticFunction.gradient(xK).multiplyByScalar(-1).sum(p.multiplyByScalar(beta));
            } else {
                nextGradient = quadraticFunction.gradient(xK).multiplyByScalar(-1);
            }
        }
        return new Pair(xK, quadraticFunction.apply(xK));
    }
}
