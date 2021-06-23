package methods;

import utils.FunctionExpression;
import utils.Matrix;
import utils.DoubleVector;

public class OneDimensionalNewton extends AbstractNewton implements Newton {
    public OneDimensionalNewton(FunctionExpression function) {
        super(function);
    }

    @Override
    protected DoubleVector makeIteration(DoubleVector vector) {
        iterationsNumber++;
        DoubleVector gradient = function.gradient(vector);
        Matrix hessian = function.hessian(vector);
        GaussSolver gaussSolver = new GaussSolver(hessian, gradient.multiplyByScalar(-1), epsilon);
        DoubleVector pk = gaussSolver.solve();
        double alpha = findAlpha(vector, pk);
        return vector.sum(pk.multiplyByScalar(alpha));
    }
}
