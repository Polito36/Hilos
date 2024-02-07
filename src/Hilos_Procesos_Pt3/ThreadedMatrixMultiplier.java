package Hilos_Procesos_Pt3;

class ThreadedMatrixMultiplier implements Runnable { 
	private final MatrixMultiplier multiplier; 
	private final Matrix matrix1; 
	private final Matrix matrix2; 
	private Matrix result;

	public ThreadedMatrixMultiplier(Matrix matrix1, Matrix matrix2) { 
		this.multiplier = new MatrixMultiplier(); 
		this.matrix1 = matrix1; 
		this.matrix2 = matrix2; 
		}
	
	public Matrix getResult() { 
		return result; 
		}
	
	@Override 
	public void run() { 
		result = multiplier.multiply(matrix1, matrix2); 
		} 
	}

