package PracticaFinal_Pt1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public final static int PUERTO = 5249;
    public final static String PALABRA_CLAVE = "java"; // Palabra clave para cerrar la conexión

    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) { // Inicializa el servidor en el puerto especificado
            System.out.println("Iniciando servidor...OK");

            while (true) { // Bucle infinito para aceptar conexiones de clientes
                try (Socket clientSocket = serverSocket.accept(); // Acepta la conexión entrante del cliente
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Para leer datos del cliente
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) { // Para enviar datos al cliente

                    System.out.println("Cliente conectado desde " + clientSocket.getInetAddress() + ":" + clientSocket.getPort()); // Imprime la dirección IP y el puerto del cliente
                    out.println("¡Bienvenido al servidor de chat! Escribe '" + PALABRA_CLAVE + "' para salir."); // Envía un mensaje de bienvenida al cliente
                    out.println("Comienza el chat:"); // Indica que comienza el chat


                    // Hilo para enviar mensajes desde el servidor al cliente
                    Thread sendMessageThread = new Thread(() -> {
                        try {
                            String serverMessage;
                            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
                            while ((serverMessage = serverInput.readLine()) != null) {
                                out.println(serverMessage); // Enviar mensaje al cliente
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    sendMessageThread.start(); // Inicia el hilo para enviar mensajes al cliente

                    // Leer mensajes del cliente y mostrarlos en la consola del servidor
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Cliente: " + inputLine); // Imprime el mensaje recibido del cliente
                        if (inputLine.equalsIgnoreCase(PALABRA_CLAVE)) {
                            break; // Cierra la conexión si el cliente envía la palabra clave
                        }
                    }
                    // Mensaje de despedida
                    out.println("¡Hasta luego!");

                    // Cierra el socket cuando se detecta la palabra clave
                    clientSocket.close();

                } catch (IOException e) {
                    System.err.println("Error al aceptar la conexión del cliente: " + e.getMessage());
                } 

                // Verifica si se ha detectado la palabra clave en el bucle principal y sale si se detecta
                if (PALABRA_CLAVE.equalsIgnoreCase(PALABRA_CLAVE)) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.runServer();
    }
}
