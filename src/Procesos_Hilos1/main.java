package Procesos_Hilos1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el número de hilos que desea crear: ");
        int numThreads = scanner.nextInt();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        ArrayList<FactorialCalculator> calculators = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            System.out.println("Hilo " + (i + 1) + ": Ingrese un número para calcular su factorial: ");
            int number = scanner.nextInt();
            FactorialCalculator calculator = new FactorialCalculator(number);
            calculators.add(calculator);
            executor.execute(calculator);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error al esperar la finalización de los hilos: " + e.getMessage());
        }

        // Mostrar resultados al final
        for (FactorialCalculator calculator : calculators) {
            System.out.println("El factorial de " + calculator.number + " es: " + calculator.getFactorialResult());
        }

        scanner.close();
    }
}

