package edu.masanz.da.juegored.client.manager;

import edu.masanz.da.juegored.client.model.Sala;
import edu.masanz.da.juegored.client.model.UserSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import static edu.masanz.da.juegored.core.Consts.PORT_UDP;

public class PlayerManager {

    public static void startClient(Sala sala) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(sala.getHost(), sala.getPuerto());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());

                String name = UserSession.getInstance().getNickname();

                // Hilo para escuchar mensajes del servidor sin bloquear la escritura
                new Thread(() -> {
                    while (in.hasNextLine()) {
                        System.out.println("\n" + in.nextLine());
                    }
                }).start();

                System.out.println("Ya puedes escribir mensajes:");
                while (true) {
                    //out.println(name + ": " + userInput.nextLine());
                }

            } catch (IOException e) {
                System.out.println("No se pudo conectar al servidor. ¿Está encendido?");
            }
        }).start();
    }

}
