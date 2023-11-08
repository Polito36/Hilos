package hilos;

public class CFilPrincipal {
    public static void main(String[] args) {
        int numHijos = 5; // Puedes obtener este valor del usuario o de otra manera
        int maxNumHijos = 10; // Límite superior

        if (numHijos > maxNumHijos) {
            System.out.println("Número de procesos hijos exageradamente grande. Limitado a " + maxNumHijos);
            numHijos = maxNumHijos;
        }

        CFil[] hijos = new CFil[numHijos];
        for (int i = 0; i < numHijos; i++) {
            // Crea instancias de CFil con diferentes temporizaciones
            hijos[i] = new CFil("Hijo " + i);
            hijos[i].setTemporizacion(i * 1000); // Temporización diferente para cada hijo
            hijos[i].start(); // Inicia el hilo hijo
        }

        // Espera a que todos los hilos hijos terminen
        for (int i = 0; i < numHijos; i++) {
            try {
                hijos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Proceso padre ha terminado.");
    }
}
