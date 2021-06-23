import methods.*;
import utils.FunctionExpression;
import utils.Pair;
import utils.DoubleVector;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        System.out.println("first func");
//        print(TestFunctions.firstFunction());
        System.out.println("second func");
        print(TestFunctions.secondFunction());
        System.out.println("third func");
        print(TestFunctions.thirdFunction());
        System.out.println("fourth func");
        print(TestFunctions.forthFunction());
        System.out.println("fifth func");
        print(TestFunctions.fifthFunction());
    }

    private static void print(Pair pair) {
        DoubleVector startVector = pair.getStartVector();
        FunctionExpression functionExpression = pair.getFunctionExpression();
        printMethod(startVector, new ClassicNewton(functionExpression));
        printMethod(startVector, new OneDimensionalNewton(functionExpression));
        printMethod(startVector, new DescentNewton(functionExpression));
        printMethod(startVector, new Powell(functionExpression));
        printMethod(startVector, new DFP(functionExpression));
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
