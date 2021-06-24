package methods;

import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Matrix;

public class DFP extends AbstractQuasiNewton implements Newton {
    public DFP(FunctionExpression function) {
        super(function);
    }

    @Override
    protected Matrix getG(Matrix G, DoubleVector delX, DoubleVector delW) {
        DoubleVector vk = G.multiplyByVector(delW);
        Matrix firstThing = delX.multiplyByTransposed().multiplyByScalar(1 / delW.scalarMultiplication(delX));
        Matrix secondThing = vk.multiplyByTransposed().multiplyByScalar(1 / vk.scalarMultiplication(delW));
        return G.sum(firstThing.multiplyByScalar(-1)).sum(secondThing.multiplyByScalar(-1));
    }
}
