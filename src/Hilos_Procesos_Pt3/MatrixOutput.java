package Hilos_Procesos_Pt3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

class MatrixOutput { 
	public void displayMatrix(Matrix matrix) { 
		int rows = matrix.getRows(); 
		int columns = matrix.getColumns();

	for (int i = 0; i < rows; i++) { 
		for (int j = 0; j < columns; j++) { 
			System.out.print(matrix.getElement(i, j) + " "); 
			} 
		System.out.println(); 
		} 
	}
	
	public void saveMatrixToFile(Matrix matrix, String filePath) throws FileNotFoundException { 
		File file = new File(filePath); 
		if (!file.exists()) { 
			try { 
				file.createNewFile(); 
				} catch (Exception e) { 
					e.printStackTrace(); 
					return; 
					} 
			}
	
	Scanner scanner = new Scanner((Readable) matrix); 
	scanner.useDelimiter("\s+");
	
	try (FileWriter writer = new FileWriter(file)) { 
		for (int i = 0; i < matrix.getRows(); i++) { 
			for (int j = 0; j < matrix.getColumns(); j++) { 
				writer.write(scanner.nextInt() + " "); 
				} 
			writer.write("\n"); 
			} 
		writer.flush(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
			} 
	} 
}

