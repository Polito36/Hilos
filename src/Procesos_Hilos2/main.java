package Procesos_Hilos2;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Solicitar al usuario el número de hilos por consola
        System.out.print("Ingrese el número de hilos: ");
        
        Scanner scanner = new Scanner(System.in);
        
        // Leer el número de hilos ingresado por el usuario
        int numThreads = scanner.nextInt();
        
        scanner.close();

        // Crear un array de hilos
        Thread[] threads = new Thread[numThreads];

        // Bucle para crear, iniciar y esperar a que cada hilo termine
        for (int i = 0; i < numThreads; i++) {
            // Crear un nuevo hilo y asignarle una instancia de la clase CalcularPotenciade2 con el índice como exponente
            threads[i] = new Thread(new CalcularPotenciade2(i));
            
            // Iniciar el hilo
            threads[i].start();
        }

        try {
            // Bucle para esperar a que cada hilo termine su ejecución
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            // Manejar la excepción si hay un problema al esperar la finalización de los hilos
            e.printStackTrace();
        }
    }
}
