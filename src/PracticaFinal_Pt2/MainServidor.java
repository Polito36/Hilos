package PracticaFinal_Pt2;

import java.io.IOException;

public class MainServidor {

    public static void main(String[] args) {
        int maxClientes = Integer.parseInt(args[0]);
        Servidor servidor = new Servidor(maxClientes);
        servidor.runServer();
    }
}


