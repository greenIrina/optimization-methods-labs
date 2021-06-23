package utils;

public class Pair {
    private FunctionExpression functionExpression;
    private DoubleVector startVector;

    public Pair(FunctionExpression functionExpression, DoubleVector startVector) {
        this.functionExpression = functionExpression;
        this.startVector = startVector;
    }

    public FunctionExpression getFunctionExpression() {
        return functionExpression;
    }

    public void setFunctionExpression(FunctionExpression functionExpression) {
        this.functionExpression = functionExpression;
    }

    public DoubleVector getStartVector() {
        return startVector;
    }

    public void setStartVector(DoubleVector startVector) {
        this.startVector = startVector;
    }
}
