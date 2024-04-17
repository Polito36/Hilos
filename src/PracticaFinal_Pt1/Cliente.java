package PracticaFinal_Pt1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    // Puerto y dirección IP del servidor
    public static final int PUERTO = 5249;
    public static final String HOST = "10.6.14.61";

    public void runCliente() {
        try (
            // Establece una conexión con el servidor
            Socket socket = new Socket(HOST, PUERTO);
            // Para enviar mensajes al servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Para recibir mensajes del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Para leer la entrada del usuario desde la consola
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor...");

            // Inicia un nuevo hilo para leer mensajes del servidor de forma asíncrona
            Thread readMessageThread = new Thread(() -> {
                try {
                    String response;
                    // Lee los mensajes del servidor mientras la conexión esté activa
                    while ((response = in.readLine()) != null) {
                        // Si el servidor envía "FIN", cierra la conexión
                        if (response != null && response.equalsIgnoreCase("FIN")) {
                            break;
                        } else if (response != null) {
                            // Imprime los mensajes recibidos del servidor
                            System.out.println("Servidor: " + response);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readMessageThread.start(); // Inicia el hilo para leer mensajes del servidor

            String inputLine;
            System.out.println("Comienza el chat:");
            // Lee la entrada del usuario y la envía al servidor
            while ((inputLine = consoleInput.readLine()) != null) {
                out.println(inputLine);
                // Si el usuario escribe "FIN", termina el bucle
                if (inputLine.equalsIgnoreCase("FIN")) {
                    break;
                }
            }

            // Notifica al servidor que el cliente va a cerrar la conexión
            System.out.println("Enviando FIN al servidor...");
            out.println("FIN");

            // Cierre del cliente
            System.out.println("Cerrando cliente...OK");
        } catch (UnknownHostException e) {
            System.err.println("No se pudo encontrar el host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
