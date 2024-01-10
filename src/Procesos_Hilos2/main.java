package Procesos_Hilos2;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
        // Solicitar el número de hilos por consola
        System.out.print("Ingrese el número de hilos: ");
        Scanner scanner = new Scanner(System.in);
        int numThreads = scanner.nextInt();
        scanner.close();

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new CalcularPotenciade2(i));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
