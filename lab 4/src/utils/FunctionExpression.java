package utils;

import java.util.function.Function;

public class FunctionExpression {
    private final Function<DoubleVector, Double> function;
    private final Function<DoubleVector, DoubleVector> gradient;
    private final Function<DoubleVector, Matrix> hessian;

    public FunctionExpression(Function<DoubleVector, Double> function, Function<DoubleVector, DoubleVector> gradient,
                              Function<DoubleVector, Matrix> hessian) {
        this.function = function;
        this.gradient = gradient;
        this.hessian = hessian;
    }

    public double apply(DoubleVector x) {
        return function.apply(x);
    }

    public DoubleVector gradient(DoubleVector x) {
        return gradient.apply(x);
    }

    public Matrix hessian(DoubleVector x) {
        return hessian.apply(x);
    }
}
