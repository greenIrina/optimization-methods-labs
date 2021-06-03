import generators.TestWriter;
import matrices.Matrix;
import matrices.ProfileMatrix;
import matrices.RegularMatrix;
import solvers.GaussSolver;
import solvers.LUSolver;
import solvers.Solver;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        TestWriter.generateLUTests();
//        TestWriter.generateHilbertLuTests();
//        TestWriter.readAndSolveLU();
//        TestWriter.readAndSolveHilbert();
//        TestWriter.printAllTableLU();
//        TestWriter.printAllTableHilbert();
//        TestWriter.readAndSolveGaussLU();
//        TestWriter.readAndSolveGaussHilbert();
//        TestWriter.printAllTableLUGauss();
//        TestWriter.printAllTableHilbertGauss();
        Path filePath = Path.of("./testsLU/testLU10_0.txt");
        try (Scanner scanner = new Scanner(filePath)) {
            int N;
            List<Double> diagonal = new ArrayList<>(), al = new ArrayList<>(), au = new ArrayList<>();
            List<Integer> ia = new ArrayList<>();
            double[] b;
            scanner.nextLine();
            N = scanner.nextInt();
            b = new double[N];
            scanner.nextLine();
            scanner.nextLine();
            int size = scanner.nextInt();
            for (int i = 0; i < size; i++) {
                diagonal.add(scanner.nextDouble());
            }
            scanner.nextLine();
            scanner.nextLine();
            size = scanner.nextInt();
            for (int i = 0; i < size; i++) {
                al.add(scanner.nextDouble());
            }
            scanner.nextLine();
            scanner.nextLine();
            size = scanner.nextInt();
            for (int i = 0; i < size; i++) {
                au.add(scanner.nextDouble());
            }
            scanner.nextLine();
            scanner.nextLine();
            size = scanner.nextInt();
            for (int i = 0; i < size; i++) {
                ia.add(scanner.nextInt());
            }
            scanner.nextLine();
            scanner.nextLine();
            for (int i = 0; i < N; i++) {
                b[i] = scanner.nextDouble();
            }
            ProfileMatrix profileMatrix = new ProfileMatrix(N, diagonal, al, au, ia);
            Solver solver = new LUSolver(N, profileMatrix);
            double[] x = solver.solve(b);
            for (double el : x) {
                System.out.print(el + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
