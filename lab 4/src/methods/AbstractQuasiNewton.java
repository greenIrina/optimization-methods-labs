package methods;

import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Matrix;

public abstract class AbstractQuasiNewton extends AbstractNewton implements Newton {
    public AbstractQuasiNewton(FunctionExpression function) {
        super(function);
    }

    @Override
    public DoubleVector solve(DoubleVector start) {
        iterationsNumber++;
        int dim = start.size();
        Matrix G = Matrix.unary(dim);
        DoubleVector W = function.gradient(start).multiplyByScalar(-1);
        DoubleVector deltaW = new DoubleVector(new double[dim]);
        DoubleVector x0 = new DoubleVector(start);
        DoubleVector P = new DoubleVector(W);
        double alpha = findAlpha(x0, P);
        DoubleVector x1 = x0.sum(P.multiplyByScalar(alpha));
        DoubleVector deltaX1 = x1.sum(x0.multiplyByScalar(-1));
        DoubleVector x = new DoubleVector(x1);
        DoubleVector deltaX = new DoubleVector(deltaX1);
        if (deltaX.sqrtLength() < epsilon) {
            return x;
        }
        return makeIteration(deltaX, deltaW, W, G, x);
    }

    protected DoubleVector makeIteration(DoubleVector deltaX, DoubleVector deltaW, DoubleVector W, Matrix G,
                                         DoubleVector x) {
        return x;
    }
}
