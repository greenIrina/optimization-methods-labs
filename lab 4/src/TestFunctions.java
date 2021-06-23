import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Matrix;
import utils.Pair;

import java.util.function.Function;

public class TestFunctions {
    public static Pair firstFunction() {
        Function<DoubleVector, Double> function = (x) -> (x.get(0) * x.get(0) + x.get(1) * x.get(1) - 1.2 * x.get(0) * x.get(1));
        Function<DoubleVector, DoubleVector> gradient = (x) -> (new DoubleVector(new double[]
                {2 * x.get(0) - 1.2 * x.get(1), 2 * x.get(1) - 1.2 * x.get(0)}));
        Function<DoubleVector, Matrix> hessian = (x) -> (new Matrix(new double[][]{{2, -1.2}, {-1.2, 2}}));
        FunctionExpression functionExpression1 = new FunctionExpression(function, gradient, hessian);
        final DoubleVector startVector1 = new DoubleVector(new double[]{4, 1});
        return new Pair(functionExpression1, startVector1);
    }

    public static Pair secondFunction() {
        Function<DoubleVector, Double> function = (x) -> (100 * Math.pow(x.get(1) - x.get(0) * x.get(0), 2)
                + Math.pow(1 - x.get(0), 2));
        Function<DoubleVector, DoubleVector> gradient = (x) -> (new DoubleVector(new double[]
                {400 * Math.pow(x.get(0), 3) - 400 * x.get(0) * x.get(1) + 2 * x.get(0) - 2,
                        200 * x.get(1) - 200 * Math.pow(x.get(0), 2)}));
        Function<DoubleVector, Matrix> hessian = (x) -> (new Matrix(new double[][]
                {{1200 * Math.pow(x.get(0), 2) - 400 * x.get(1) + 2, -400 * x.get(0)}, {-400 * x.get(0), 200.0}}));
        FunctionExpression functionExpression2 = new FunctionExpression(function, gradient, hessian);
        final DoubleVector startVector2 = new DoubleVector(new double[]{1, 1});
        return new Pair(functionExpression2, startVector2);
    }


    public static Pair thirdFunction() {
        Function<DoubleVector, Double> function = (x) -> (Math.pow(Math.pow(x.get(0), 2) + x.get(1) - 11, 2) +
                Math.pow(x.get(0) + Math.pow(x.get(1), 2) - 7, 2));
        Function<DoubleVector, DoubleVector> gradient = (x) -> (new DoubleVector(new double[]{
                4 * (Math.pow(x.get(0), 3) + x.get(0) * x.get(1) - 11 * x.get(0)) + 2 * (x.get(0) + Math.pow(x.get(1), 2) - 7),
                2 * (Math.pow(x.get(0), 2) + x.get(1) - 11) + 4 * (x.get(0) * x.get(1) + Math.pow(x.get(1), 3) - 7 * x.get(1))
        }));
        Function<DoubleVector, Matrix> hessian = (x) -> (new Matrix(new double[][]{
                {12 * Math.pow(x.get(0), 2) + 4 * x.get(1) - 42,
                        4 * (x.get(0) + x.get(1))},
                {4 * (x.get(0) + x.get(1)),
                        4 * x.get(0) + 12 * Math.pow(x.get(1), 2) - 26}
        }));
        FunctionExpression functionExpression3 = new FunctionExpression(function, gradient, hessian);
        final DoubleVector startVector3 = new DoubleVector(new double[]{1, 1});
        return new Pair(functionExpression3, startVector3);
    }

    public static Pair forthFunction() {
        Function<DoubleVector, Double> function = (x) -> (Math.pow(x.get(0) + 10 * x.get(1), 2)
                + 5 * Math.pow(x.get(2) - x.get(3), 2) + Math.pow(x.get(1) - 2 * x.get(2), 4)
                + 10 * Math.pow(x.get(0) - x.get(3), 4));
        Function<DoubleVector, DoubleVector> gradient = (x) -> (new DoubleVector(new double[]{
                2 * (20 * (x.get(0) - x.get(3)) * (x.get(0) - x.get(3)) * (x.get(0) - x.get(3)) + x.get(0) + 10 * x.get(1)),
                4 * (5 * (x.get(0) + 10 * x.get(1)) +
                        (x.get(1) - 2 * x.get(2)) * (x.get(1) - 2 * x.get(2)) * (x.get(1) - 2 * x.get(2))),
                10 * (x.get(2) - x.get(3)) -
                        8 * (x.get(1) - 2 * x.get(2)) * (x.get(1) - 2 * x.get(2)) * (x.get(1) - 2 * x.get(2)),
                10 * (-4 * (x.get(0) - x.get(3)) * (x.get(0) - x.get(3)) * (x.get(0) - x.get(3)) + x.get(3) - x.get(2))
        }));
        Function<DoubleVector, Matrix> hessian = (x) -> (new Matrix(new double[][]{
                {120 * (x.get(0) - x.get(3)) * (x.get(0) - x.get(3)) + 2, 20,                              0,
                        -120 *
                                (x.get(0) -
                                        x.get(3)) *
                                (x.get(0) -
                                        x.get(3))},
                {20,                                                      12 * (x.get(1) - 2 * x.get(2)) *
                        (x.get(1) - 2 * x.get(2)) + 200, -24 *
                        (x.get(1) -
                                2 *
                                        x.get(2)) *
                        (x.get(1) -
                                2 *
                                        x.get(2)), 0},
                {0,                                                       -24 * (x.get(1) - 2 * x.get(2)) *
                        (x.get(1) - 2 * x.get(2)),       48 *
                        (x.get(1) -
                                2 *
                                        x.get(2)) *
                        (x.get(1) -
                                2 *
                                        x.get(2)) +
                        10,         -10},
                {-120 * (x.get(0) - x.get(3)) * (x.get(0) -
                        x.get(3)),               0,                               -10,        120 *
                        (x.get(0) -
                                x.get(3)) *
                        (x.get(0) -
                                x.get(3)) +
                        10}
        }));
        FunctionExpression functionExpression4 = new FunctionExpression(function, gradient, hessian);
        final DoubleVector startVector4 = new DoubleVector(new double[]{1, 1, 1, 1});
        return new Pair(functionExpression4, startVector4);
    }

    public static Pair fifthFunction() {
        Function<DoubleVector, Double> function = (x) -> (-2.0 / (0.25 * Math.pow(x.get(0) - 1, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1) -
                1.0 / (0.25 * Math.pow(x.get(0) - 2, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1) + 100);
        Function<DoubleVector, DoubleVector> gradient = (x) -> (new DoubleVector(new double[]
                {(648 * (x.get(0) - 2)) /
                        Math.pow(9 * Math.pow(x.get(0), 2) - 36 * x.get(0) + 4 * Math.pow(x.get(1), 2) - 8 * x.get(1) + 76, 2) +
                        (x.get(0) - 1) / Math.pow(0.25 * Math.pow(x.get(0) - 1, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1, 2),
                        2.0 / 9 * (x.get(1) - 1) *
                                (2.0 / Math.pow(0.25 * Math.pow(x.get(0) - 1, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1, 2) +
                                        1.0 / Math.pow(0.25 * Math.pow(x.get(0) - 2, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1, 2))}));
        Function<DoubleVector, Matrix> hessian = (x) -> {
            final double number = 4.0 / 9 * (-1 + x.get(1)) * (-(-1 + x.get(0)) /
                    Math.pow(1 + 1.0 / 4 * Math.pow(-1 + x.get(0), 2) +
                            1.0 / 9 * Math.pow(-1 + x.get(1), 2), 3) -
                    (23328 * (-2 + x.get(0))) /
                            Math.pow(76 - 36 * x.get(0) + 9 * x.get(0) * x.get(0) -
                                    8 * x.get(1) + 4 * x.get(1) * x.get(1), 3));
            return (new Matrix(new double[][]
                    {{(-Math.pow(-1 + x.get(0), 2) /
                            Math.pow(1 + 0.25 * Math.pow(-1 + x.get(0), 2) + 1.0 / 9 * Math.pow(-1 + x.get(1), 2), 3) +
                            1.0 / Math.pow(1 + 0.25 * Math.pow(-1 + x.get(0), 2) + 1.0 / 9 * Math.pow(-1 + x.get(1), 2), 2) -
                            (23328 * Math.pow(-2 + x.get(0), 2)) /
                                    Math.pow(76 - 36 * x.get(0) + 9 * x.get(0) * x.get(0) - 8 * x.get(1) +
                                            4 * x.get(1) * x.get(1), 3) + 648 / Math.pow(76 - 36 * x.get(0) +
                                    9 * x.get(0) * x.get(0) -
                                    8 * x.get(1) + 4 * x.get(1) * x.get(1),
                            2)),
                            number},

                            {number,
                                    2.0 / 9 * (4.0 / 9 * Math.pow(x.get(1) - 1, 2) * (-2.0 /
                                            Math.pow(1.0 / 4 * Math.pow(x.get(0) - 1, 2) +
                                                            1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1,
                                                    3) - 1.0 / Math.pow(1.0 / 4 *
                                            Math.pow(x.get(0) - 2,
                                                    2) + 1.0 / 9 *
                                            Math.pow(x.get(
                                                    1) -
                                                    1, 2) +
                                            1, 3)) + 1.0 /
                                            Math.pow(1.0 /
                                                    4 *
                                                    Math.pow(x.get(
                                                            0) -
                                                                    2,
                                                            2) +
                                                    1.0 /
                                                            9 *
                                                            Math.pow(x.get(
                                                                    1) -
                                                                            1,
                                                                    2) +
                                                    1, 2) +
                                            2.0 /
                                                    Math.pow(1.0 / 4 * Math.pow(x.get(0) - 1, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1,
                                                            2))}}));
        };
        FunctionExpression functionExpression5 = new FunctionExpression(function, gradient, hessian);
        final DoubleVector startVector5 = new DoubleVector(new double[]{1, 1});
        return new Pair(functionExpression5, startVector5);
    }

    public static Pair secondRandomFunction() {
        Function<DoubleVector, Double> function = (x) -> (x.get(0) * x.get(0) + x.get(1) * x.get(1) - 2 * x.get(0)
                + 2 * x.get(1) + 2.5);
        Function<DoubleVector, DoubleVector> gradient = (x) -> (new DoubleVector(new double[]{
                2 * (x.get(0) - 1),
                2 * (x.get(1) + 1)
        }));
        Function<DoubleVector, Matrix> hessian = (x) -> (new Matrix(new double[][]{
                {2, 0},
                {0, 2}
        }));
        FunctionExpression functionExpression = new FunctionExpression(function, gradient, hessian);
        final DoubleVector startVector = new DoubleVector(new double[]{-3, -4});
        return new Pair(functionExpression, startVector);
    }


    public static Pair firstRandomFunction() {
        Function<DoubleVector, Double> function = (x) -> (Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)));
        Function<DoubleVector, DoubleVector> gradient = (x) -> (new DoubleVector(new double[]{
                (x.get(0) - 3) / (Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2))),
                (5 * (x.get(1) + 2)) / (Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)))
        }));
        Function<DoubleVector, Matrix> hessian = (x) -> (new Matrix(new double[][]{
                {(5 * Math.pow(x.get(1) + 2, 2))
                        / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3)),
                        -5 * (x.get(0) - 3) * (x.get(1) + 2)
                                / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3))},
                {-5 * (x.get(0) - 3) * (x.get(1) + 2)
                        / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3)),
                        (5 * Math.pow(x.get(1) + 2, 2))
                                / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3))}
        }));
        FunctionExpression functionExpression = new FunctionExpression(function, gradient, hessian);
        final DoubleVector startVector = new DoubleVector(new double[]{-3, -4});
        return new Pair(functionExpression, startVector);
    }
}
