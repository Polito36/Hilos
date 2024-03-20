package PracticaFinal_Pt1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public Socket socket;
	public DataOutputStream dataOutputStream;
	public static final int PUERTO = 2640;
	public static final String HOST = "localhost";
	
	public Cliente() throws UnknownHostException, IOException {
		this.socket = new Socket(HOST, PUERTO);
	}
		
    public void runCliente() throws IOException {

        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor...");

            String inputLine;
            while ((inputLine = consoleInput.readLine()) != null) {
                out.println(inputLine);
                if (inputLine.equalsIgnoreCase("FIN")) {
                    break;
                }

                String response = in.readLine();
                System.out.println("Servidor: " + response);
                if (response.equalsIgnoreCase("FIN")) {
                    break;
                }
            }

            System.out.println("Cerrando cliente...OK");
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}

