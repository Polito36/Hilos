package Procesos_Hilos3;

class Buscador implements Runnable {
    private int[] vector;
    private int inicio;
    private int fin;
    private int objetivo;
    private boolean encontrado;

    public Buscador(int[] vector, int inicio, int fin, int objetivo) {
        this.vector = vector;
        this.inicio = inicio;
        this.fin = fin;
        this.objetivo = objetivo;
        this.encontrado = false;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            if (vector[i] == objetivo) {
                System.out.println("¡Valor encontrado en la posición " + i + " por el hilo " + Thread.currentThread().getId() + "!");
                encontrado = true;
                return;
            }
        }
    }

    public boolean isEncontrado() {
        return encontrado;
    }
}