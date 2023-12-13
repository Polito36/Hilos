package Procesos_Hilos2;

public class main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java PowerOfTwoThreads <numero_hilos>");
            return;
        }

        int numThreads = Integer.parseInt(args[0]);
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new CalcularPotenciade2(i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar la finalización de un hilo: " + e.getMessage());
            }
        }

        CalcularPotenciade2[] calculators = new CalcularPotenciade2[numThreads];
        for (int i = 0; i < numThreads; i++) {
            calculators[i] = new CalcularPotenciade2(i);
        }

        // Ordenar calculadoras por potencia de 2
        for (int i = 0; i < numThreads; i++) {
            for (int j = i + 1; j < numThreads; j++) {
                if (calculators[i].getResult() > calculators[j].getResult()) {
                	CalcularPotenciade2 temp = calculators[i];
                    calculators[i] = calculators[j];
                    calculators[j] = temp;
                }
            }
        }

        // Imprimir resultados en orden ascendente de potencias de 2
        for (CalcularPotenciade2 calculator : calculators) {
            System.out.println("2^" + calculator.exponent + " = " + calculator.getResult());
        }
    }
}
