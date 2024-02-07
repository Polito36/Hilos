package Hilos_Procesos_Pt3;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MatrixInput input = new MatrixInput();
        Matrix matrix1 = input.readMatrixFromConsole();
        Matrix matrix2 = input.readMatrixFromConsole();

        if (!MatrixMultiplier.canMultiply(matrix1, matrix2)) {
            System.out.println("Las matrices no pueden ser multiplicadas.");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ThreadedMatrixMultiplier multiplier = new ThreadedMatrixMultiplier(matrix1, matrix2);
        executor.submit(multiplier);
        executor.shutdown();

        while (!executor.isTerminated()) {
            // Wait for all threads to finish
        }

        MatrixOutput output = new MatrixOutput();
        output.displayMatrix(multiplier.getResult());
        try {
			output.saveMatrixToFile(multiplier.getResult(), "result.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("¿Desea realizar otra multiplicación de matrices? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("s")) {
            main(args);
        }
    }
}
