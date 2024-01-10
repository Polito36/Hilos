package Procesos_Hilos2;

class CalcularPotenciade2 implements Runnable {
	private int power;

    public CalcularPotenciade2(int power) {
        this.power = power;
    }

    @Override
    public void run() {
        long result = 1;
        for (int i = 0; i < power; i++) {
            result *= 2;
        }
        System.out.println("2^" + power + " = " + result);
    }
}
