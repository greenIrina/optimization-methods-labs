import methods.ClassicNewton;
import methods.DescentNewton;
import methods.Newton;
import methods.OneDimensionalNewton;
import utils.FunctionExpression;
import utils.Pair;
import utils.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("first func");
        print(TestFunctions.firstFunction());
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
        Vector startVector = pair.getStartVector();
        FunctionExpression functionExpression = pair.getFunctionExpression();
        printMethod(startVector, new ClassicNewton(functionExpression));
        printMethod(startVector, new OneDimensionalNewton(functionExpression));
        printMethod(startVector, new DescentNewton(functionExpression));
    }

    private static void printMethod(Vector startVector, Newton newton) {
        Vector solution = newton.solve(startVector);
        System.out.println("x = [" + solution.get(0) + ", " + solution.get(1) + "]");
        newton.printFirst();
        newton.printSecond();
        System.out.println("Iterations: " + newton.getIterationsNumber());
        System.out.println();
    }
}
