package methods;

import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Matrix;

public class DFP extends AbstractQuasiNewton implements Newton {
    public DFP(FunctionExpression function) {
        super(function);
    }

    @Override
    public DoubleVector makeIteration(DoubleVector deltaX, DoubleVector deltaW, DoubleVector W, Matrix G,
                              DoubleVector x) {
        while (deltaX.sqrtLength() >= epsilon) {
            iterationsNumber++;
            DoubleVector newW = function.gradient(x).multiplyByScalar(-1);
            deltaW = newW.sum(W.multiplyByScalar(-1));
            DoubleVector newV = G.multiplyByVector(deltaW);
            Matrix newG = G.sum(deltaX.multiplyByTransposed()
                    .multiplyByScalar(1.0 / deltaW.scalarMultiplication(deltaX)).multiplyByScalar(-1))
                    .sum(newV.multiplyByTransposed().multiplyByScalar(1.0 / newV.scalarMultiplication(deltaW))
                            .multiplyByScalar(-1)
                    );
            DoubleVector P = newG.multiplyByVector(newW);
            double alpha = findAlpha(x, P);
            DoubleVector newX = x.sum(P.multiplyByScalar(alpha));
            DoubleVector newDeltaX = newX.sum(x.multiplyByScalar(-1));
            x = newX;
            deltaX = newDeltaX;
            W = newW;
        }
        return x;
    }
}
