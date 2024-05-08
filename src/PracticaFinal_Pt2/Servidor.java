package PracticaFinal_Pt2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public final static int PUERTO = 5249;
    public final static String PALABRA_CLAVE_SERVIDOR = "java";
    public final static String PALABRA_CLAVE_CLIENTE = "adios";
    private final int maxClientes;
    private int contadorClientes = 0;
    private List<ClienteThread> clientes = new ArrayList<>();

    public Servidor(int maxClientes) {
        this.maxClientes = maxClientes;
    }

    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Iniciando servidor...OK");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                contadorClientes++;
                ClienteThread cliente = new ClienteThread(clientSocket, contadorClientes);
                clientes.add(cliente);
                cliente.start();

                if (clientes.size() == maxClientes) {
                    System.out.println("Máximo número de clientes alcanzado. Cerrando el servidor...");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private class ClienteThread extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private int numeroCliente;

        public ClienteThread(Socket socket, int numeroCliente) {
            this.socket = socket;
            this.numeroCliente = numeroCliente;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("¡Bienvenido al servidor de chat! Escribe '" + PALABRA_CLAVE_CLIENTE + "' para salir.");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Cliente " + numeroCliente + ": " + inputLine);
                    if (inputLine.equalsIgnoreCase(PALABRA_CLAVE_SERVIDOR)) {
                        cerrarTodosChats();
                    } else if (inputLine.equalsIgnoreCase(PALABRA_CLAVE_CLIENTE)) {
                        cerrarChat();
                    } else {
                        enviarMensajeATodos(inputLine);
                    }
                }

                socket.close();
                clientes.remove(this);
                System.out.println("Cliente " + numeroCliente + " desconectado.");
            } catch (IOException e) {
                System.err.println("Error en la conexión del cliente: " + e.getMessage());
            }
        }

        private void cerrarChat() {
            out.println("¡Hasta luego!");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            interrupt();
        }

        private void cerrarTodosChats() {
            for (ClienteThread cliente : clientes) {
                if (cliente != this) {
                    cliente.out.println("¡El servidor ha cerrado la conexión!");
                    cliente.cerrarChat();
                }
            }
            clientes.remove(this);
            System.out.println("Cliente " + numeroCliente + " desconectado.");
        }


        private void enviarMensajeATodos(String mensaje) {
            for (ClienteThread cliente : clientes) {
                if (cliente != this) {
                    cliente.out.println("Cliente " + numeroCliente + ": " + mensaje);
                }
            }
        }
    }   
    }