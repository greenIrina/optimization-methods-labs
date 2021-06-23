public interface Solver {
    Pair findMin(DoubleVector startVector);

    long getIterationsNumber();
}
