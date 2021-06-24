package methods;

import utils.FunctionExpression;
import utils.DoubleVector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractNewton implements Newton {
    protected static final double epsilon = 1e-8;
    protected final FunctionExpression function;
    protected int iterationsNumber = 0;
    protected List<Double> first;
    protected List<Double> second;

    public AbstractNewton(FunctionExpression function) {
        this.function = function;
    }

    public DoubleVector solve(DoubleVector startVector) {
        iterationsNumber = 0;
        first = new ArrayList<>();
        second = new ArrayList<>();
        DoubleVector vector = new DoubleVector(startVector);
        DoubleVector newVector = makeIteration(vector);
        first.add(vector.get(0));
        second.add(vector.get(1));
        if (vector.sum(newVector.multiplyByScalar(-1)).sqrtLength() < epsilon)
            return vector;
        while (vector.sum(newVector.multiplyByScalar(-1)).sqrtLength() >= epsilon) {
            vector = new DoubleVector(newVector);
            first.add(vector.get(0));
            second.add(vector.get(1));
            newVector = makeIteration(vector);
        }
        iterationsNumber--;
        return newVector;
    }

    protected abstract DoubleVector makeIteration(DoubleVector vector);

    protected double findAlpha(DoubleVector x, DoubleVector pk) {
        final Function<Double, Double> alphaFunction = (alpha) -> (function.apply(x.sum(pk.multiplyByScalar(alpha))));
        return new GoldenSection(-10, 10, epsilon / 10, alphaFunction).findMin();
    }

    public int getIterationsNumber() {
        return iterationsNumber;
    }

    public void printFirst() {
        System.out.print("a = [");
        System.out.print(first.stream().map(Object::toString).collect(Collectors.joining(", ")));
        System.out.print("]");
        System.out.println();
    }

    public void printSecond() {
        System.out.print("b = [" +
                second.stream().map(Object::toString).collect(Collectors.joining(", ")) +
                "]");
        System.out.println();
    }
}
