import methods.*;
import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Pair;

public class Main {
    public static void main(String[] args) {
//        System.out.println("first func");
//        print(TestFunctions.firstFunction());
//        System.out.println("second func");
//        print(TestFunctions.secondFunction());
//        System.out.println("third func");
//        print(TestFunctions.thirdFunction());
        System.out.println("fourth func");
        print(TestFunctions.forthFunction());
//        System.out.println("fifth func");
//        print(TestFunctions.fifthFunction());
    }

    private static void print(Pair pair) {
        int[] X = new int[]{-1, 2};
        int[] Y = new int[]{2, 1};
        for (int i : X) {
            for (int j : Y) {
                DoubleVector startVector = new DoubleVector(new double[]{i, j, j, i});
//                DoubleVector startVector = pair.getStartVector();
                FunctionExpression functionExpression = pair.getFunctionExpression();
//        printMethod(startVector, new ClassicNewton(functionExpression));
                printMethod(startVector, new OneDimensionalNewton(functionExpression));
//        printMethod(startVector, new DescentNewton(functionExpression));
//                DoubleVector startVector = new DoubleVector(new double[]{i, j});

                printMethod(startVector, new Powell(functionExpression));
                printMethod(startVector, new DFP(functionExpression));
            }
        }


    }

    private static void printMethod(DoubleVector startVector, Newton newton) {
        DoubleVector solution = newton.solve(startVector);
        System.out.println(newton.getClass().getSimpleName());
        System.out.println("start = [" + printVector(startVector) + "]");
        System.out.println("x = [" + printVector(solution) + "]");
//        newton.printFirst();
//        newton.printSecond();
        System.out.println("Iterations: " + newton.getIterationsNumber());
        System.out.println();
    }

    private static String printVector(DoubleVector vector) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < vector.size() - 1; i++) {
            ans.append(vector.get(i)).append(", ");
        }
        ans.append(vector.get(vector.size() - 1));
        return ans.toString();
    }
}
