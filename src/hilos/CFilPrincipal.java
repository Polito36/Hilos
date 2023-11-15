package hilos;

import java.util.Scanner;

public class CFilPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numHijos = 0;
        int maxNumHijos = 10;
        int temporizacion = 0;

        while (numHijos <= 0 || numHijos > maxNumHijos) {
            System.out.println("Ingrese el número de procesos hijos (hasta 10): ");
            numHijos = scanner.nextInt();
        }

        System.out.println("Ingrese la temporización en milisegundos para todos los hijos: ");
        temporizacion = scanner.nextInt();

        CFil[] hijos = new CFil[numHijos];

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Modificar temporización para que los hijos terminen antes que el padre");
            System.out.println("2. Modificar temporización para que los hijos terminen después que el padre");
            System.out.println("3. Salir");

            System.out.println("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    for (int i = 0; i < numHijos; i++) {
                        hijos[i] = new CFil("Hijo " + i);
                        hijos[i].setTemporizacion(i * 500); // Modificar para que terminen antes
                        hijos[i].start();
                    }
                    break;
                case 2:
                    for (int i = 0; i < numHijos; i++) {
                        hijos[i] = new CFil("Hijo " + i);
                        hijos[i].setTemporizacion(i * 2000); // Modificar para que terminen después
                        hijos[i].start();
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            // Espera a que todos los hilos hijos terminen
            for (int i = 0; i < numHijos; i++) {
                try {
                    hijos[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (opcion != 3);

        System.out.println("Proceso padre ha terminado.");
        scanner.close();
    }
}
