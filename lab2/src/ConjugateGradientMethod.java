public class ConjugateGradientMethod extends AbstractSolver {

    public ConjugateGradientMethod(double epsilon, QuadraticFunction quadraticFunction) {
        super(epsilon, quadraticFunction);
    }

    @Override
    protected Pair calcMin(Vector x, double xFunc, Vector gradient, double length) {
        Vector vectorP = gradient.multiplyByScalar(-1);
        while (length >= epsilon) {
            iterationsNumber++;
            Vector ap = quadraticFunction.getA().multiplyByVector(vectorP);
            alpha = length / ap.scalarMultiplication(vectorP);
            Vector nextX = x.sum(vectorP.multiplyByScalar(alpha)), nextGradient = gradient.sum(ap.multiplyByScalar(alpha));
            double nextGradientLength = nextGradient.scalarMultiplication(nextGradient), beta;
            if (iterationsNumber % x.size() == 0) {
                beta = 0;
            } else {
                beta = nextGradientLength / length;
            }
            vectorP = nextGradient.multiplyByScalar(-1).sum(vectorP.multiplyByScalar(beta));
            gradient = nextGradient;
            length = nextGradientLength;
            x = nextX;
        }
        return new Pair(x, quadraticFunction.apply(x));
    }
}
