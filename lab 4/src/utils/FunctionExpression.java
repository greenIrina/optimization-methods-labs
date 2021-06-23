package utils;

import java.util.function.Function;

public class FunctionExpression {
    private final Function<Vector, Double> function;
    private final Function<Vector, Vector> gradient;
    private final Function<Vector, Matrix> hessian;

    public FunctionExpression(Function<Vector, Double> function, Function<Vector, Vector> gradient,
                              Function<Vector, Matrix> hessian) {
        this.function = function;
        this.gradient = gradient;
        this.hessian = hessian;
    }

    public double apply(Vector x) {
        return function.apply(x);
    }

    public Vector gradient(Vector x) {
        return gradient.apply(x);
    }

    public Matrix hessian(Vector x) {
        return hessian.apply(x);
    }
}
