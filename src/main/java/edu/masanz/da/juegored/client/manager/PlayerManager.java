package edu.masanz.da.juegored.client.manager;

import edu.masanz.da.juegored.client.model.Sala;
import edu.masanz.da.juegored.client.model.UserSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PlayerManager {

    public static boolean buscarServidores = false;
    public static boolean conexionAbierta = false;

    public static void startClient(Sala sala) {
        new Thread(() -> {
            try {
                conexionAbierta = true;
                Socket socket = new Socket(sala.getHost(), sala.getPuerto());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());

                String name = UserSession.getInstance().getNickname();

                // Hilo para escuchar mensajes del servidor sin bloquear la escritura
                new Thread(() -> {
                    while (conexionAbierta && in.hasNextLine()) {
                        System.out.println("\n" + in.nextLine());
                    }
                    conexionAbierta = false;
                    System.out.println("hilo startClient ESCUCHAR muerto");
                }).start();

                System.out.println("Ya puedes escribir mensajes:");
                while (conexionAbierta) {
                    //out.println(name + ": " + userInput.nextLine());
                    Thread.sleep(1000);
                }
                conexionAbierta = false;
                System.out.println("hilo startClient ESCRIBIR muerto");
                socket.close();
            } catch (IOException e) {
                System.out.println("No se pudo conectar al servidor. ¿Está encendido?");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
