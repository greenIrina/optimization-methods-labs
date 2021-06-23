public class ClassicNewton extends AbstractNewton implements Newton {
    public ClassicNewton(FunctionExpression function) {
        super(function);
    }

    @Override
    protected Vector makeIteration(Vector vector) {
        iterationsNumber++;
        Vector gradient = function.gradient(vector);
        Matrix hessian = function.hessian(vector);
        GaussSolver gaussSolver = new GaussSolver(hessian, gradient.multiplyByScalar(-1), epsilon);
        Vector pk = gaussSolver.solve();
        return vector.sum(pk);
    }
}
