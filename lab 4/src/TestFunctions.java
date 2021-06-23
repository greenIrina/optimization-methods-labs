import java.util.function.Function;

public class TestFunctions {
    public static Pair firstFunction() {
        Function<Vector, Double> function = (x) -> (x.get(0) * x.get(0) + x.get(1) * x.get(1) - 1.2 * x.get(0) * x.get(1));
        Function<Vector, Vector> gradient = (x) -> (new Vector(new double[]
                {2 * x.get(0) - 1.2 * x.get(1), 2 * x.get(1) - 1.2 * x.get(0)}));
        Function<Vector, Matrix> hessian = (x) -> (new Matrix(new double[][]{{2, -1.2}, {-1.2, 2}}));
        FunctionExpression functionExpression1 = new FunctionExpression(function, gradient, hessian);
        final Vector startVector1 = new Vector(new double[]{4, 1});
        return new Pair(functionExpression1, startVector1);
    }

    public static Pair secondFunction() {
        Function<Vector, Double> function = (x) -> (100 * Math.pow(x.get(1) - x.get(0) * x.get(0), 2)
                + Math.pow(1 - x.get(0), 2));
        Function<Vector, Vector> gradient = (x) -> (new Vector(new double[]
                {400 * Math.pow(x.get(0), 3) - 400 * x.get(0) * x.get(1) + 2 * x.get(0) - 2,
                        200 * x.get(1) - 200 * Math.pow(x.get(0), 2)}));
        Function<Vector, Matrix> hessian = (x) -> (new Matrix(new double[][]
                {{1200 * Math.pow(x.get(0), 2) - 400 * x.get(1) + 2, -400 * x.get(0)}, {-400 * x.get(0), 200.0}}));
        FunctionExpression functionExpression2 = new FunctionExpression(function, gradient, hessian);
        final Vector startVector2 = new Vector(new double[]{-1.2, 1});
        return new Pair(functionExpression2, startVector2);
    }

    public static Pair thirdFunction() {
        Function<Vector, Double> function = (x) -> (-2.0 / (0.25 * Math.pow(x.get(0) - 1, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1) -
                1.0 / (0.25 * Math.pow(x.get(0) - 2, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1) + 100);
        Function<Vector, Vector> gradient = (x) -> (new Vector(new double[]
                {(648 * (x.get(0) - 2)) /
                        Math.pow(9 * Math.pow(x.get(0), 2) - 36 * x.get(0) + 4 * Math.pow(x.get(1), 2) - 8 * x.get(1) + 76, 2) +
                        (x.get(0) - 1) / Math.pow(0.25 * Math.pow(x.get(0) - 1, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1, 2),
                        2.0 / 9 * (x.get(1) - 1) *
                                (2.0 / Math.pow(0.25 * Math.pow(x.get(0) - 1, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1, 2) +
                                        1.0 / Math.pow(0.25 * Math.pow(x.get(0) - 2, 2) + 1.0 / 9 * Math.pow(x.get(1) - 1, 2) + 1, 2))}));
        Function<Vector, Matrix> hessian = (x) -> {
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
        FunctionExpression functionExpression3 = new FunctionExpression(function, gradient, hessian);
        final Vector startVector3 = new Vector(new double[]{1, 1});
        return new Pair(functionExpression3, startVector3);
    }

    public static Pair forthFunction() {
        Function<Vector, Double> function = (x) -> (Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)));
        Function<Vector, Vector> gradient = (x) -> (new Vector(new double[]{
                (x.get(0) - 3) / (Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2))),
                (5 * (x.get(1) + 2)) / (Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)))
        }));
        Function<Vector, Matrix> hessian = (x) -> (new Matrix(new double[][]{
                {(5 * Math.pow(x.get(1) + 2, 2))
                        / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3)),
                        -5 * (x.get(0) - 3) * (x.get(1) + 2)
                                / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3))},
                {-5 * (x.get(0) - 3) * (x.get(1) + 2)
                        / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3)),
                        (5 * Math.pow(x.get(1) + 2, 2))
                                / (Math.pow(Math.sqrt(Math.pow(x.get(0) - 3, 2) + 5 * Math.pow(x.get(1) + 2, 2)), 3))}
        }));
        FunctionExpression functionExpression4 = new FunctionExpression(function, gradient, hessian);
        final Vector startVector4 = new Vector(new double[]{-3, -4});
        return new Pair(functionExpression4, startVector4);
    }

    public static Pair fifthFunction() {
        Function<Vector, Double> function = (x) -> (x.get(0) * x.get(0) + x.get(1) * x.get(1) - 2 * x.get(0)
                + 2 * x.get(1) + 2.5);
        Function<Vector, Vector> gradient = (x) -> (new Vector(new double[]{
                2 * (x.get(0) - 1),
                2 * (x.get(1) + 1)
        }));
        Function<Vector, Matrix> hessian = (x) -> (new Matrix(new double[][]{
                {2, 0},
                {0, 2}
        }));
        FunctionExpression functionExpression5 = new FunctionExpression(function, gradient, hessian);
        final Vector startVector5 = new Vector(new double[]{-3, -4});
        return new Pair(functionExpression5, startVector5);
    }
}
