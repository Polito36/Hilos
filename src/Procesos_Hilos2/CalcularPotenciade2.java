
package Procesos_Hilos2;


class CalcularPotenciade2 implements Runnable {
    private int power;

    public CalcularPotenciade2(int power) {
        this.power = power;
    }

    @Override
    public void run() {
        // Inicialización de la variable result para almacenar el resultado
        long result = 1;

        // Bucle para calcular la potencia de 2 elevado al exponente
        for (int i = 0; i < power; i++) {
            result *= 2;
        }

        // Imprimir el resultado de la potencia de 2
        System.out.println("2^" + power + " = " + result);
    }
}
