package methods;

import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Matrix;

import java.util.ArrayList;

public abstract class AbstractQuasiNewton extends AbstractNewton implements Newton {
    public AbstractQuasiNewton(FunctionExpression function) {
        super(function);
    }

    @Override
    protected DoubleVector makeIteration(DoubleVector vector) {
        iterationsNumber++;
        return vector;
    }

    @Override
    public DoubleVector solve(DoubleVector start) {
        iterationsNumber = 1;
        first = new ArrayList<>();
        second = new ArrayList<>();
        DoubleVector pred = start;
        first.add(start.get(0));
        second.add(start.get(1));

        Matrix G = Matrix.diagonal(start.size());
        DoubleVector w = function.gradient(start).multiplyByScalar(-1);
        final DoubleVector p = w;
        double a = findAlpha(start, p);
        DoubleVector next = start.sum(p.multiplyByScalar(a));
        first.add(next.get(0));
        second.add(next.get(1));

        while (next.sum(pred.multiplyByScalar(-1)).sqrtLength() >= epsilon) {
            iterationsNumber++;
            DoubleVector newW = function.gradient(next).multiplyByScalar(-1);
            DoubleVector delX = next.sum(pred.multiplyByScalar(-1));
            DoubleVector delW = newW.sum(w.multiplyByScalar(-1));
            G = getG(G, delX, delW);
            final DoubleVector nextP = new DoubleVector(G.multiplyByVector(newW));
            final DoubleVector finalNext = next;
            a = findAlpha(finalNext, nextP);
            pred = next;
            w = newW;
            next = next.sum(nextP.multiplyByScalar(a));
            first.add(next.get(0));
            second.add(next.get(1));
        }
        return next;
    }

    protected abstract Matrix getG(Matrix G, DoubleVector delX, DoubleVector delW);
}
