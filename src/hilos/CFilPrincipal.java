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
            System.out.println("1. Modificar temporización para 1 hijo que termine antes que el padre");
            System.out.println("2. Modificar temporización para 1 hijo que termine después que el padre");
            System.out.println("3. Asegurar que el padre termine después de cualquier hijo (para " + numHijos + " hijos)");
            System.out.println("4. Iniciar ejecución con temporizaciones crecientes (para " + numHijos + " hijos)");
            System.out.println("5. Salir");

            System.out.println("Seleccione una opción: ");
            opcion = scanner.nextInt();

            if (opcion == 1 || opcion == 2) {
                System.out.println("Proceso padre iniciado.");
            }

            if (opcion == 1) {
                hijos[0] = new CFil("Hijo 1");
                hijos[0].setTemporizacion(500); // Modificar para que termine antes
                hijos[0].start();
                try {
                    hijos[0].join(); // Espera a que el hilo hijo termine antes de continuar con el padre
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (opcion == 2) {
                hijos[0] = new CFil("Hijo 1");
                hijos[0].setTemporizacion(2000); // Modificar para que termine después
                hijos[0].start();
                try {
                    hijos[0].join(); // Espera a que el hilo hijo termine antes de continuar con el padre
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (opcion == 3) {
                for (int i = 0; i < numHijos; i++) {
                    hijos[i] = new CFil("Hijo " + (i + 1));
                    hijos[i].setTemporizacion(temporizacion);
                    hijos[i].start();
                    try {
                        hijos[i].join(); // Espera a que cada hilo hijo termine antes de continuar con el padre
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (opcion == 4) {
                if (numHijos > 0) {
                    for (int i = 0; i < numHijos; i++) {
                        hijos[i] = new CFil("Hijo " + (i + 1));
                        hijos[i].setTemporizacion(i * 1000); // Temporizaciones crecientes
                        hijos[i].start();
                    }

                    // Espera a que todos los hilos hijos terminen
                    for (int i = 0; i < numHijos; i++) {
                        try {
                            hijos[i].join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Número de procesos hijos debe ser mayor que 0.");
                }
            } else if (opcion == 5) {
                System.out.println("Proceso padre terminado.");
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (true);

        scanner.close();
    }
}




 