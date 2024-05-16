package PracticaFinal_Pt2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static final int PUERTO = 5249; // Puerto del servidor al que se conectará el cliente
    public static final String HOST = "10.6.13.245"; // Dirección IP del servidor

    public void runCliente() {
        try (
            // Se establece la conexión con el servidor utilizando un Socket
            Socket socket = new Socket(HOST, PUERTO);
            // Para enviar mensajes al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Para recibir mensajes del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Para leer la entrada del usuario desde la consola
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor...");

            // Se crea un hilo para escuchar los mensajes del servidor
            Thread readMessageThread = new Thread(() -> {
                try {
                    String response;
                    // Se lee continuamente desde el servidor y se imprime en la consola
                    while ((response = in.readLine()) != null) {
                        System.out.println("Servidor: " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readMessageThread.start(); // Se inicia el hilo de lectura

            String inputLine;
            System.out.println("Comienza el chat:");
            // Se lee la entrada del usuario desde la consola y se envía al servidor
            while ((inputLine = consoleInput.readLine()) != null) {
                out.println(inputLine);
                // Si el usuario escribe "adios", se cierra la conexión y se sale del programa
                if (inputLine.equalsIgnoreCase("adios")) {
                    System.out.println("Cerrando cliente...OK");
                    socket.close();
                    System.exit(0);
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
