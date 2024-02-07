package Hilos_Procesos_Pt3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

class MatrixOutput { 
	public void displayMatrix(Matrix matrix) { 
		int rows = matrix.getRows(); 
		int columns = matrix.getColumns();

		System.out.println("La matriz resultante es:");
	for (int i = 0; i < rows; i++) { 
		for (int j = 0; j < columns; j++) { 
			System.out.print("| " + matrix.getElement(i, j) + " "); 
			}
		System.out.print("|");
		System.out.println(); 
		} 
	}
	
	public void saveMatrixToFile(Matrix matrix, String filePath) {
	    try (PrintWriter writer = new PrintWriter(filePath)) {
	        int rows = matrix.getRows();
	        int columns = matrix.getColumns();

	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < columns; j++) {
	                writer.print(matrix.getElement(i, j) + " ");
	            }
	            writer.println();
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
 
}

