public class Pair {
    private DoubleVector value;
    private double funcValue;

    public Pair(DoubleVector value, double funcValue) {
        this.value = value;
        this.funcValue = funcValue;
    }

    public DoubleVector getValue() {
        return value;
    }

    public void setValue(DoubleVector value) {
        this.value = value;
    }

    public double getFuncValue() {
        return funcValue;
    }

    public void setFuncValue(double funcValue) {
        this.funcValue = funcValue;
    }
}
