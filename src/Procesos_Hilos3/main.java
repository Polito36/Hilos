package Procesos_Hilos3;

import java.util.Random;
import java.util.Scanner;

public class main {
    public static boolean encontrado = false;
    public static int cont = 0; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario el tamaño del vector, el valor a buscar y el número de hilos
        System.out.println("Ingrese el tamaño del vector:");
        int size = scanner.nextInt();

        System.out.println("Ingrese el valor a buscar:");
        int targetValue = scanner.nextInt();

        System.out.println("Ingrese el número de hilos:");
        int numThreads = scanner.nextInt();

        // Validar los argumentos de entrada
        if (size <= 0 || numThreads <= 0 || numThreads > size) {
            System.out.println("Los argumentos deben ser positivos y el número de hilos no puede ser mayor que el tamaño del vector.");
            System.exit(1);
        }

        int[] vector = new int[size];
        Random random = new Random();

        // Llenar el vector con números aleatorios en un rango específico
        for (int i = 0; i < size; i++) {
            vector[i] = random.nextInt(1000);
        }

        Thread[] threads = new Thread[numThreads];

        // Calcular la cantidad de posiciones que cada hilo debe procesar
        int positionsPerThread = size / numThreads;
        int remainingPositions = size % numThreads;
        int currentIndex = 0;

        // Crear y ejecutar los hilos
        for (int i = 0; i < numThreads; i++) {
            int start = currentIndex;
            int end = currentIndex + positionsPerThread - 1;

            if (i < remainingPositions) {
                end++;
            }

            threads[i] = new Thread(new Buscador(vector, start, end, targetValue, i + 1));
            threads[i].start();

            currentIndex = end + 1;
        }

        // Proceso padre: buscar en las posiciones no asignadas a los hilos
        for (int i = currentIndex; i < size; i++) {
            if (vector[i] == targetValue) {
                System.out.println("Proceso padre encontró el valor " + targetValue + " en la posición " + i);
            }
        }

        // Actualizar la variable 'encontrado' si al menos un hilo encontró el valor
        if (cont >= 1) {
            encontrado = true;
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Verificar si se encontró el valor en algún hilo
        if (!encontrado) {
            System.out.println("Ningún hilo o proceso padre encontró el valor " + targetValue + " en el vector.");
        }
    }
}
