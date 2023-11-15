package hilos;

import java.util.Scanner;

class CFil extends Thread {
    private String nombre;
    private int temporizacion;

    public CFil(String nombre) {
        this.nombre = nombre;
        this.temporizacion = 0; // Valor predeterminado
    }

    public String getNombre() {
        return nombre;
    }

    public void setTemporizacion(int temporizacion) {
        this.temporizacion = temporizacion;
    }

    @Override
    public void run() {
        try {
            System.out.println("Hilo " + nombre + " iniciado.");
            // Realiza alguna tarea aquí

            // Simula la temporización del hilo
            Thread.sleep(temporizacion);

            System.out.println("Hilo " + nombre + " terminado.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}