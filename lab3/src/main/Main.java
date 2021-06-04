import generators.TestWriter;

public class Main {
    public static void main(String[] args) {
        TestWriter.generateLUTests();
        TestWriter.generateHilbertLuTests();
        TestWriter.readAndSolveLU();
        TestWriter.readAndSolveHilbert();
        TestWriter.printAllTableLU();
        TestWriter.printAllTableHilbert();
        TestWriter.readAndSolveGaussLU();
        TestWriter.readAndSolveGaussHilbert();
        TestWriter.printAllTableLUGauss();
        TestWriter.printAllTableHilbertGauss();
    }
}
