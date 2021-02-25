public class Main {
    public static void main(String[] args) {
        // func f(x) = (log(10,(x-2)))^2 + (log(10,(10-x)))^2 - x^(0.2)
        // range [6; 9.9]
        // min f(x): x = 8.7269, f(x) = -0.8460
        double leftBound = 6, rightBound = 9.9;
        Solver dichotomy = new Dichotomy(leftBound, rightBound);
        double xDichotomy = dichotomy.getMinX(), funcDichotomy = dichotomy.getMinFunc();
        System.out.println("Точка минимумса: " + xDichotomy + ", минимум: " + funcDichotomy);
    }
}
