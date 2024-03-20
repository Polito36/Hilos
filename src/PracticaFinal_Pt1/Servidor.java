package PracticaFinal_Pt1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public Socket socket;
	public ServerSocket serverSocket;
	public final static int PUERTO = 2640;
	public DataOutputStream dataOutputStream;
	public String mensaje;
	
	public Servidor() throws IOException {
		this.serverSocket = new ServerSocket(PUERTO); //Inicializamos el servidor
		this.socket = new Socket();
	}
	
	
    public void runServer() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Iniciando servidor...OK");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Cliente: " + inputLine);
                    if (inputLine.equalsIgnoreCase("FIN")) {
                        break;
                    }

                    System.out.print("Servidor: ");
                    String response = consoleInput.readLine();
                    out.println(response);
                    if (response.equalsIgnoreCase("FIN")) {
                        break;
                    }
                }
            }

            System.out.println("Cerrando servidor...OK");
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

