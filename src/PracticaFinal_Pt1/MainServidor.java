package PracticaFinal_Pt1;

import java.io.IOException;

public class MainServidor {

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        System.out.println("Servidor iniciado");
        servidor.runServer();
    }
}


