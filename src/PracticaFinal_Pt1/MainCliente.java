package PracticaFinal_Pt1;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainCliente {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Cliente cliente = new Cliente();
        cliente.runCliente();
    }
}

