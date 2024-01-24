
package Procesos_Hilos1;


import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;


class FactorialCalculator implements Runnable {

    int number;
    private long factorialResult;

    FactorialCalculator(int number) {
        this.number = number;
    }

    // Método privado que realiza el cálculo del factorial
    private long calculateFactorial(int n) {
        long factorial = 1;
        // Bucle que multiplica los números desde 2 hasta n para calcular el factorial
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    // Método público para obtener el resultado del cálculo del factorial
    public long getFactorialResult() {
        return factorialResult;
    }

    @Override
    public void run() {
        // Llama al método calculateFactorial para calcular el factorial y asigna el resultado
        factorialResult = calculateFactorial(number);
    }
}
