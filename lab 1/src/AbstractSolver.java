public abstract class AbstractSolver implements Solver {
    public double calcFunc(double x) {
        return  -Math.pow(x, 0.2) + Math.pow(Math.log10(x - 2), 2) + Math.pow(Math.log10(10 - x), 2);
    }
}
