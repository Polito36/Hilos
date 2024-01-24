
package Procesos_Hilos1;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar al usuario el número de hilos que desea crear
        System.out.println("Ingrese el número de hilos que desea crear: ");
        int numThreads = scanner.nextInt();

        // Crear un ExecutorService con un número fijo de hilos. 
        //Permite la creación, ejecución y control de tareas Runnable de manera asíncrona.
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
        // Lista para almacenar objetos FactorialCalculator
        ArrayList<FactorialCalculator> calculators = new ArrayList<>();

        // Bucle para solicitar números y ejecutar hilos para calcular factoriales
        for (int i = 0; i < numThreads; i++) {
            // Solicitar al usuario un número para calcular su factorial
            System.out.println("Hilo " + (i + 1) + ": Ingrese un número para calcular su factorial: ");
            int number = scanner.nextInt();
            
            FactorialCalculator calculator = new FactorialCalculator(number);
            
            calculators.add(calculator);
            
            // Ejecutar el hilo para calcular el factorial del número
            executor.execute(calculator);
        }

        executor.shutdown();
        try {
            // Esperar hasta 10 segundos para que todos los hilos terminen
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // Manejar la excepción si hay un problema al esperar la finalización de los hilos
            System.err.println("Error al esperar la finalización de los hilos: " + e.getMessage());
        }

        // Mostrar resultados al final
        for (FactorialCalculator calculator : calculators) {
            System.out.println("El factorial de " + calculator.number + " es: " + calculator.getFactorialResult());
        }

        scanner.close();
    }
}
