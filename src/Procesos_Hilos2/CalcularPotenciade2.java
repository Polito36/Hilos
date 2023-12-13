package Procesos_Hilos2;

class CalcularPotenciade2 implements Runnable {
    int exponent;
    private long result;

    CalcularPotenciade2(int exponent) {
        this.exponent = exponent;
    }

    private long calculatePowerOfTwo(int exp) {
        return (long) Math.pow(2, exp);
    }

    public long getResult() {
        return result;
    }

    @Override
    public void run() {
        result = calculatePowerOfTwo(exponent);
    }
}
