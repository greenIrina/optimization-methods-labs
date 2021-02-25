public class Main {
    public static void main(String[] args) {
        // func y(x) = (log(10,(x-2)))^2 + (log(10,(10-x)))^2 - x^(0.2)
        // range [6; 9.9]
        // min y(x): x = 8.7269
        double leftBound = 6, rightBound = 9.9;
        Dichotomy dichotomy = new Dichotomy(leftBound, rightBound);
        double xDichotomy = dichotomy.getMinX(), funcDichotomy = dichotomy.getMinFunc();
        System.out.println("Точка минимумса: " + xDichotomy + ", минимум: " + funcDichotomy);
    }
}
