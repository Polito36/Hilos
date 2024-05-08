package PracticaFinal_Pt2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static final int PUERTO = 5249;
    public static final String HOST = "localhost";

    public void runCliente() {
        try (
            Socket socket = new Socket(HOST, PUERTO);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor...");

            Thread readMessageThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println("Servidor: " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readMessageThread.start();

            String inputLine;
            System.out.println("Comienza el chat:");
            while ((inputLine = consoleInput.readLine()) != null) {
                out.println(inputLine);
                if (inputLine.equalsIgnoreCase("FIN")) {
                    break;
                }
            }

            System.out.println("Enviando FIN al servidor...");
            out.println("FIN");

            System.out.println("Cerrando cliente...OK");
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.runCliente();
    }
}
