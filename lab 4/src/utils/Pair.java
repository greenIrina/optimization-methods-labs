package utils;

public class Pair {
    private FunctionExpression functionExpression;
    private Vector startVector;

    public Pair(FunctionExpression functionExpression, Vector startVector) {
        this.functionExpression = functionExpression;
        this.startVector = startVector;
    }

    public FunctionExpression getFunctionExpression() {
        return functionExpression;
    }

    public void setFunctionExpression(FunctionExpression functionExpression) {
        this.functionExpression = functionExpression;
    }

    public Vector getStartVector() {
        return startVector;
    }

    public void setStartVector(Vector startVector) {
        this.startVector = startVector;
    }
}
