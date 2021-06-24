package methods;

import utils.DoubleVector;
import utils.FunctionExpression;
import utils.Matrix;


public class Powell extends AbstractQuasiNewton implements Newton {
    public Powell(FunctionExpression function) {
        super(function);
    }


    protected Matrix getG(Matrix G, DoubleVector delX, DoubleVector delW) {
        DoubleVector delXStar = delX.sum(new DoubleVector(G.multiplyByVector(delW)));
        return G.sum(delXStar
                .multiplyByTransposed(delXStar)
                .multiplyByScalar(1 / delW.scalarMultiplication(delXStar))
                .multiplyByScalar(-1));
    }
}
