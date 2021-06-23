package methods;

import utils.FunctionExpression;
import utils.Matrix;
import utils.Vector;

public class DescentNewton extends AbstractNewton implements Newton {
    public DescentNewton(FunctionExpression function) {
        super(function);
    }

    @Override
    protected Vector makeIteration(Vector vector) {
        iterationsNumber++;
        Vector gradient = function.gradient(vector);
        Matrix hessian = function.hessian(vector);
        GaussSolver gaussSolver = new GaussSolver(hessian, gradient.multiplyByScalar(-1), epsilon);
        Vector pk = gaussSolver.solve();
        if (pk.scalarMultiplication(gradient) > 0) {
            pk = gradient.multiplyByScalar(-1);
        }
        double alpha = findAlpha(vector, pk);
        return vector.sum(pk.multiplyByScalar(alpha));
    }
}
