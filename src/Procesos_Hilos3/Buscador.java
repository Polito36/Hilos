package Procesos_Hilos3;

class Buscador implements Runnable {
    private int[] vector;       
    private int inicio;         
    private int fin;            
    private int objetivo;       
    private int numeroHilo;     
    private boolean encontrado; 

    // Constructor 
    public Buscador(int[] vector, int inicio, int fin, int objetivo, int numeroHilo) {
        this.vector = vector;
        this.inicio = inicio;
        this.fin = fin;
        this.objetivo = objetivo;
        this.numeroHilo = numeroHilo;
        this.encontrado = false;
    }

 
    @Override
    public void run() {
        // Iterar sobre el rango asignado al hilo y buscar el valor objetivo
        for (int i = inicio; i <= fin; i++) {
            if (vector[i] == objetivo) {
                System.out.println("¡El Valor " + objetivo +" se ha encontrado en la posición " + i + " por el hilo " + numeroHilo + "!");
                encontrado = true;
                main.cont++; 
                return;      
            }
        }
    }

    // Método que devuelve el estado de la variable encontrado
    public boolean isEncontrado() {
        return encontrado;
    }
}
