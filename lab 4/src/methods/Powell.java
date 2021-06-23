package methods;

import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Matrix;


public class Powell extends AbstractQuasiNewton implements Newton {
    public Powell(FunctionExpression function) {
        super(function);
    }

    @Override
    public DoubleVector makeIteration(DoubleVector deltaX, DoubleVector deltaW, DoubleVector W, Matrix G, DoubleVector x) {

        while (deltaX.sqrtLength() < epsilon) {
            iterationsNumber++;
            DoubleVector deltaWaveX = new DoubleVector(deltaX);
            deltaWaveX = deltaWaveX.sum(G.multiplyByVector(deltaW));
            DoubleVector newW = function.gradient(x).multiplyByScalar(-1);
            deltaW = newW.sum(W.multiplyByScalar(-1));
            Matrix newG = G.sum(deltaWaveX.multiplyByTransposed()
                    .multiplyByScalar(1.0 / deltaW.scalarMultiplication(deltaWaveX))
                    .multiplyByScalar(-1));
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
