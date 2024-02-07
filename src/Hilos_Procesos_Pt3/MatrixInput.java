package Hilos_Procesos_Pt3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class MatrixInput {
    public Matrix readMatrixFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el número de filas:");
        int rows = scanner.nextInt();
        System.out.println("Ingrese el número de columnas:");
        int columns = scanner.nextInt();
        Matrix matrix = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println("Ingrese el elemento en la posición (" + (i + 1) + ", " + (j + 1) + "):");
                int value = scanner.nextInt();
                matrix.setElement(i, j, value);
            }
        }

        return matrix;
    }

    public Matrix readMatrixFromFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        Matrix matrix = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = scanner.nextInt();
                matrix.setElement(i, j, value);
            }
        }

        scanner.close();
        return matrix;
    }
}
