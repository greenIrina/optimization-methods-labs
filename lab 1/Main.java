import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // func f(x) = (log(10,(x-2)))^2 + (log(10,(10-x)))^2 - x^(0.2)
        // range [6; 9.9]
        // min f(x): x = 8.7269, f(x) = -0.8460
        double leftBound = 6, rightBound = 9.9;
        double epsilon;
        int testCount = 9;
        Function<Double, Double> function = (x -> -Math.pow(x, 0.2) + Math.pow(Math.log10(x - 2), 2) + Math.pow(Math.log10(10 - x), 2));
        for (int i = -1; i > -testCount; i--) {
            epsilon = Double.parseDouble(String.format("1e%d", i));

            Solver dichotomy = new Dichotomy(leftBound, rightBound, epsilon, function);
            System.out.println("Метод дихотомии. Заданная точность: " + epsilon
                    + ". Точка минимума: " + dichotomy.getMinX() + ", минимум: " + dichotomy.getMinFunc());

            Solver goldenSection = new GoldenSection(leftBound, rightBound, epsilon, function);
            System.out.println("Метод золотого сечения. Заданная точность: " + epsilon
                    + ". Точка минимума: " + goldenSection.getMinX() + ", минимум: " + goldenSection.getMinFunc());

            Solver fibonacci = new Fibonacci(leftBound, rightBound, epsilon, function);
            System.out.println("Метод Фибоначчи. Заданная точность: " + epsilon
                    + ". Точка минимума: " + fibonacci.getMinX() + ", минимум: " + fibonacci.getMinFunc());

            Solver parabola = new Parabola(leftBound, rightBound, epsilon, function);
            System.out.println("Метод Парабол. Заданная точность: " + epsilon
                    + ". Точка минимума: " + parabola.getMinX() + ", минимум: " + parabola.getMinFunc());

            Solver brent = new Brent(leftBound, rightBound, epsilon, function);
            System.out.println("Комбинированный метод Брента. Заданная точность: " + epsilon
                    + ". Точка минимума: " + brent.getMinX() + ", минимум: " + brent.getMinFunc());
            System.out.println();
        }
    }
}
