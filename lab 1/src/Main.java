public class Main {
    public static void main(String[] args) {
        // func f(x) = (log(10,(x-2)))^2 + (log(10,(10-x)))^2 - x^(0.2)
        // range [6; 9.9]
        // min f(x): x = 8.7269, f(x) = -0.8460
        double leftBound = 6, rightBound = 9.9;
        double epsilon = 1e-5;
        Solver dichotomy = new Dichotomy(leftBound, rightBound, epsilon);
        double xDichotomy = dichotomy.getMinX(), funcDichotomy = dichotomy.getMinFunc();
        System.out.println("Метод дихотомии. Точка минимума: " + xDichotomy + ", минимум: " + funcDichotomy);
        //dichotomy.printSteps();
        Solver goldenSection = new GoldenSection(leftBound, rightBound, epsilon);
        System.out.println("Метод золотого сечения. Точка минимума: " + goldenSection.getMinX()
                + ", минимум: " + goldenSection.getMinFunc());
        //goldenSection.printSteps();
        Solver fibonacci = new Fibonacci(leftBound, rightBound, epsilon);
        System.out.println("Метод Фибоначчи. Точка минимума: " + fibonacci.getMinX() + ", минимум: "
                + fibonacci.getMinFunc());
        //fibonacci.printSteps();
        Solver parabola = new Parabola(leftBound,rightBound, epsilon);
        System.out.println("Метод Парабол. Точка минимума: " + parabola.getMinX() + ", минимум: "
                + parabola.getMinFunc());
        //parabola.printSteps();
    }
}
