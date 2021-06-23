package generators;

import matrices.ProfileMatrix;
import matrices.RegularMatrix;
import solvers.GaussSolver;
import solvers.LUSolver;
import solvers.Solver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestWriter {
    private static final String luTestName = "./testsLU/testLU";
    private static final String hilbertTestName = "./testsHilbert/testHilbert";
    private static final String headerLU = "n\tk\t||x*-xk||\t||x*-xk||/||x*||";
    private static final String headerHilbert = "n\t||x*-xk||\t||x*-xk||/||x*||";

    public static void generateLUTests() {
        for (int n = 10; n < 10000; n *= 10) {
            for (int k = 0; k < 10; k++) {
                Path filePath = Path.of(luTestName + n + "_" + k + ".txt");
                createDir(filePath);
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
                    TaskPair matrixPair = TaskGenerator.generateLUTask(n, k);
                    printTask(matrixPair, bufferedWriter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void generateHilbertLuTests() {
        for (int n = 2; n < 2048; n *= 2) {
            Path filePath = Path.of(hilbertTestName + n + ".txt");
            createDir(filePath);
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)) {
                TaskPair matrixPair = TaskGenerator.generateHilbertTask(n);
                printTask(matrixPair, bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printTask(TaskPair matrixPair, BufferedWriter bufferedWriter) throws IOException {
        ProfileMatrix matrix = matrixPair.matrix;
        bufferedWriter.write("n" + System.lineSeparator());
        bufferedWriter.write(matrix.getN() + System.lineSeparator());
        List<Double> list = matrix.getDiagonal();
        printArray(list, "diagonal", bufferedWriter);
        list = matrix.getAl();
        printArray(list, "al", bufferedWriter);
        list = matrix.getAu();
        printArray(list, "au", bufferedWriter);
        List<Integer> ia = matrix.getIa();
        printArray(ia, "ia", bufferedWriter);
        double[] b = matrixPair.b;
        bufferedWriter.write("b" + System.lineSeparator());
        for (double el : b) {
            bufferedWriter.write(el + " ");
        }
    }

    private static <T> void printArray(List<T> list, String name, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(name + System.lineSeparator());
        bufferedWriter.write(list.size() + System.lineSeparator());
        for (T t : list) {
            bufferedWriter.write(t + " ");
        }
        bufferedWriter.write(System.lineSeparator());
    }

    private static void createDir(Path path) {
        if (path.getParent() != null) {
            try {
                Files.createDirectories(path.getParent());
            } catch (final IOException e) {
                System.err.println("Exception while creating file: " + e.getMessage());
            }
        }
    }

    public static void readAndSolveLU() {
        for (int n = 10; n < 10000; n *= 10) {
            for (int k = 0; k < 10; k++) {
                final String fileName = luTestName + n + "_" + k;
                final TaskPair taskPair = readAndSolve(fileName);
                if (taskPair == null) {
                    System.err.println("IOException");
                    return;
                }
                write(new LUSolver(taskPair.matrix, taskPair.b), fileName + "Answer.txt");
            }
        }
    }

    public static void readAndSolveHilbert() {
        for (int n = 2; n < 2048; n *= 2) {
            final String fileName = hilbertTestName + n;
            final TaskPair taskPair = readAndSolve(fileName);
            if (taskPair == null) {
                System.err.println("IOException");
                return;
            }
            write(new LUSolver(taskPair.matrix, taskPair.b), fileName + "Answer.txt");
        }
    }

    public static void readAndSolveGaussLU() {
        for (int n = 10; n < 10000; n *= 10) {
            for (int k = 0; k < 10; k++) {
                final String fileName = luTestName + n + "_" + k;
                final TaskPair taskPair = readAndSolve(fileName);
                if (taskPair == null) {
                    System.err.println("IOException");
                    return;
                }
                write(new GaussSolver(new RegularMatrix(taskPair.matrix), taskPair.b), fileName + "AnswerGauss.txt");
            }
        }
    }

    public static void readAndSolveGaussHilbert() {
        for (int n = 2; n < 2048; n *= 2) {
            final String fileName = hilbertTestName + n;
            final TaskPair taskPair = readAndSolve(fileName);
            if (taskPair == null) {
                System.err.println("IOException");
                return;
            }
            write(new GaussSolver(new RegularMatrix(taskPair.matrix), taskPair.b), fileName + "AnswerGauss.txt");
        }
    }

    private static TaskPair readAndSolve(String fileName) {
        Path filePath = Path.of(fileName + ".txt");
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
            return new TaskPair(new ProfileMatrix(N, diagonal, al, au, ia), b);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void write(Solver solver, String outputFileName) {
        double[] x = solver.solve();
        Path outputFilePath = Path.of(outputFileName);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFilePath)) {
            Arrays.stream(x).forEach(el -> {
                try {
                    bufferedWriter.write(el + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAllTableLUGauss() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("./luTableGauss.txt"))) {
            bufferedWriter.write(headerLU + System.lineSeparator());
            for (int n = 10; n < 10000; n *= 10) {
                for (int k = 0; k < 10; k++) {
                    try (Scanner scanner = new Scanner(Path.of(luTestName + n + "_" + k + "AnswerGauss.txt"))) {
                        double[] ans = printTable(n, scanner);
                        bufferedWriter.write(n + "\t" + k + "\t" + ans[0] + "\t" + ans[1] + System.lineSeparator());
                    }
                }
            }
            bufferedWriter.write(System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAllTableHilbertGauss() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("./hilbertTableGauss.txt"))) {
            bufferedWriter.write(headerHilbert + System.lineSeparator());
            for (int n = 2; n < 2048; n *= 2) {
                try (Scanner scanner = new Scanner(Path.of(hilbertTestName + n + "AnswerGauss.txt"))) {
                    double[] ans = printTable(n, scanner);
                    bufferedWriter.write(n + "\t" + ans[0] + "\t" + ans[1] + System.lineSeparator());
                }
            }
            bufferedWriter.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAllTableLU() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("./luTable.txt"))) {
            bufferedWriter.write(headerLU + System.lineSeparator());
            for (int n = 10; n < 10000; n *= 10) {
                for (int k = 0; k < 10; k++) {
                    try (Scanner scanner = new Scanner(Path.of(luTestName + n + "_" + k + "Answer.txt"))) {
                        double[] ans = printTable(n, scanner);
                        bufferedWriter.write(n + "\t" + k + "\t" + ans[0] + "\t" + ans[1] + System.lineSeparator());
                    }
                }
            }
            bufferedWriter.write(System.lineSeparator());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAllTableHilbert() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("./hilbertTable.txt"))) {
            bufferedWriter.write(headerHilbert + System.lineSeparator());
            for (int n = 2; n < 2048; n *= 2) {
                try (Scanner scanner = new Scanner(Path.of(hilbertTestName + n + "Answer.txt"))) {
                    double[] ans = printTable(n, scanner);
                    bufferedWriter.write(n + "\t" + ans[0] + "\t" + ans[1] + System.lineSeparator());
                }
            }
            bufferedWriter.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[] printTable(int n, Scanner scanner) {
        double[] ans = new double[2];
        double[] xReal = new double[n];
        double[] subtraction = new double[n];
        for (int i = 0; i < n; i++) {
            xReal[i] = i + 1;
            subtraction[i] = Math.pow(Math.abs(xReal[i] - scanner.nextDouble()), 2);
            xReal[i] = Math.pow(Math.abs(xReal[i]), 2);
        }
        double norm = Arrays.stream(subtraction).sum();
        norm = Math.sqrt(norm);
        double norm2 = Arrays.stream(xReal).sum();
        norm2 = Math.sqrt(norm2);
        ans[0] = norm;
        ans[1] = norm / norm2;
        return ans;
    }
}
