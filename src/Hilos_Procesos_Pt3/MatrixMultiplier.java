package Hilos_Procesos_Pt3;

class MatrixMultiplier {
    public static boolean canMultiply(Matrix matrix1, Matrix matrix2) {
        return matrix1.getColumns() == matrix2.getRows();
    }

    public Matrix multiply(Matrix matrix1, Matrix matrix2) { 
    	int rows1 = matrix1.getRows(); int columns1 = matrix1.getColumns(); 
    	int rows2 = matrix2.getRows(); int columns2 = matrix2.getColumns(); 
    	Matrix result = new Matrix(rows1, columns2);

	for (int i = 0; i < rows1; i++) { 
		for (int j = 0; j < columns2; j++) { 
			int sum = 0; for (int k = 0; k < columns1; k++) { 
				sum += matrix1.getElement(i, k) * matrix2.getElement(k, j); 
				} 
			result.setElement(i, j, sum); 
			} 
		}
	
	return result; 
    } 
 }