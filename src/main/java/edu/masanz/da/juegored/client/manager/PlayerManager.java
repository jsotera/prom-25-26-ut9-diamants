package edu.masanz.da.juegored.client.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static edu.masanz.da.juegored.core.Consts.PORT;

public class PlayerManager {

    public static void startClient() {
        try (Socket socket = new Socket("localhost", PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream());
             Scanner userInput = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Escribe tu nombre:");
            String name = userInput.nextLine();

            // Hilo para escuchar mensajes del servidor sin bloquear la escritura
            new Thread(() -> {
                while (in.hasNextLine()) {
                    System.out.println("\n" + in.nextLine());
                }
            }).start();

            System.out.println("Ya puedes escribir mensajes:");
            while (userInput.hasNextLine()) {
                out.println(name + ": " + userInput.nextLine());
            }

        } catch (IOException e) {
            System.out.println("No se pudo conectar al servidor. ¿Está encendido?");
        }
    }


}
