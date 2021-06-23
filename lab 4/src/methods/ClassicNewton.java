package methods;

import utils.FunctionExpression;
import utils.Matrix;
import utils.DoubleVector;

public class ClassicNewton extends AbstractNewton implements Newton {
    public ClassicNewton(FunctionExpression function) {
        super(function);
    }

    @Override
    protected DoubleVector makeIteration(DoubleVector vector) {
        iterationsNumber++;
        DoubleVector gradient = function.gradient(vector);
        Matrix hessian = function.hessian(vector);
        GaussSolver gaussSolver = new GaussSolver(hessian, gradient.multiplyByScalar(-1), epsilon);
        DoubleVector pk = gaussSolver.solve();
        return vector.sum(pk);
    }
}
