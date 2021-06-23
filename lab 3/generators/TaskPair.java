package generators;

import matrices.ProfileMatrix;

public class TaskPair {
    public ProfileMatrix matrix;
    public double[] b;

    public TaskPair(ProfileMatrix matrix, double[] b) {
        this.matrix = matrix;
        this.b = b;
    }
}
