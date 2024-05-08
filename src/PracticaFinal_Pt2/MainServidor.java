package PracticaFinal_Pt2;

import java.io.IOException;
import java.util.Scanner;

public class MainServidor {

    public static void main(String[] args) {
    	
    	Scanner s1 = new Scanner(System.in);
    	System.out.println("Cuantos clientes quieres? ");
    	int clientes = s1.nextInt();
 
        Servidor servidor = new Servidor(clientes);
        servidor.runServer();
    }
}


