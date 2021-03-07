public class Dichotomy extends AbstractSolver implements Solver {
    private double delta;


    public Dichotomy(double leftBound, double rightBound, double epsilon) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.epsilon = epsilon;
        this.delta = epsilon / 2;
        createLogger("D, eps=" + epsilon, 0);
        calcMinX();
    }

    private void calcMinX() {
        double epsilonN = 1, a = leftBound, b = rightBound;
        int count = 0;
        int cnt = 0;
        while (epsilonN > epsilon) {
            double x1 = (a + b - delta) / 2;
            double x2 = (a + b + delta) / 2;
            double fX1 = calcFunc(x1);
            double fX2 = calcFunc(x2);
            cnt += 2;
            count++;
            logger.writeData(values(count, a, b, x1, x2, fX1, fX2), count);
            if (fX1 - fX2 < 0) {
                b = x2;
            } else {
                a = x1;
            }

            epsilonN = (b - a) / 2;
        }
        minX = (a + b) / 2;
        minFunc = calcFunc(minX);
        cnt++;
        logger.writeCntFunc(cnt);
        logger.writeInFile();
    }
}
