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

    private long calculateFactorial(int n) {
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public long getFactorialResult() {
        return factorialResult;
    }

    @Override
    public void run() {
        factorialResult = calculateFactorial(number);
    }
}
