import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        double eps = 1e-4;

        QuadraticFunction quadraticFunction1 =
                new FunctionGenerator(1., -1.2, 1., 0., 0., 0., 16. / 5).getQuadraticFunction();
        //72*x*x -120*x*y + 72*y*y + 12*x -30*y + 25
//        QuadraticFunction quadraticFunction2 =
//                new FunctionGenerator(1., 2., 2., 2., 4., 3., Math.sqrt(5) + 3).getQuadraticFunction();
        //x*x + 2*x*y + 2*y*y + 2*x + 4*y + 3
//        QuadraticFunction quadraticFunction3 =
//                new FunctionGenerator(2.5, 0., 5, 12., 0., 2, 10).getQuadraticFunction();
        //2.5*x*x + 5*y*y +12*x +2
//        DoubleVector nullVector = new DoubleVector(Collections.nCopies(2, 0.0));
        printResult(true, quadraticFunction1, eps, new DoubleVector(new double[]{4, 1}));
//        printResult(true, quadraticFunction2, eps, nullVector);
//        printResult(true, quadraticFunction3, eps, nullVector);

//        testRandom(eps);
    }

    static void testRandom(double eps) {
        int[] N = {10, 100, 500, 1000, 2000};
        int[] K = {1, 5, 10, 50, 100, 500, 1000};
        for (int n : N) {
            for (int k : K) {
                QuadraticFunction quadraticFunctionRandom = new FunctionGenerator(n, k).getQuadraticFunction();
                System.out.println("n = " + n + ", k = " + k);
                printResult(false, quadraticFunctionRandom, eps, new DoubleVector(Collections.nCopies(n, 1.0)));
            }
        }
    }

    static void printResult(boolean printVector, QuadraticFunction quadraticFunction, double eps, DoubleVector startVector) {
        GradientDescent gradientDescent = new GradientDescent(eps, quadraticFunction);
        printMethod(printVector, gradientDescent, startVector);

        SteepestDescent steepestDescent = new SteepestDescent(eps, quadraticFunction);
        printMethod(printVector, steepestDescent, startVector);

        ConjugateGradientMethod conjugateGradientMethod = new ConjugateGradientMethod(eps, quadraticFunction);
        printMethod(printVector, conjugateGradientMethod, startVector);
        System.out.println();
    }

    static void printMethod(boolean printVector, Solver method, DoubleVector startVector) {
        System.out.println("\t" + method.getClass().getSimpleName());
        Pair answer = method.findMin(startVector);
        if (printVector) {
            System.out.print("Point: ");
            answer.getValue().getCoefficients().forEach(s -> System.out.print(s + " "));
            System.out.print(", ");
            System.out.println("func value: " + answer.getFuncValue());
        }
        System.out.println("iterations number: " + method.getIterationsNumber());
    }
}
