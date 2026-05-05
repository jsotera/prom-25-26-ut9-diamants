package edu.masanz.da.juegored;

import edu.masanz.da.juegored.client.manager.PlayerManager;
import edu.masanz.da.juegored.server.ServerManager;

import java.util.Scanner;

public class ServerTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Deseas iniciar como (S)ervidor o (C)liente?");
        String choice = sc.nextLine().trim().toUpperCase();

        if (choice.equals("S")) {
            ServerManager.publicarServidor();
        } else if (choice.equals("C")) {
            PlayerManager.buscarServidores();
        } else {
            System.out.println("Opción no válida.");
        }
    }

}
