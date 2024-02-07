package Hilos_Procesos_Pt3;

class Matrix {
    private int rows;
    private int columns;
    private int[][] data;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new int[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getElement(int row, int column) {
        return data[row][column];
    }

    public void setElement(int row, int column, int value) {
        data[row][column] = value;
    }
}
