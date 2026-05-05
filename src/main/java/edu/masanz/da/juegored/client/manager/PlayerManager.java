package edu.masanz.da.juegored.client.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import static edu.masanz.da.juegored.core.Consts.PORT_UDP;

public class PlayerManager {

    public static void startClient() {
        int puerto = 0;
        try (Socket socket = new Socket("localhost", puerto);
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

    public static void buscarServidores() {
        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(PORT_UDP)) {
                // Es importante poner un timeout para que no se bloquee siempre
                socket.setSoTimeout(5000);
                byte[] buffer = new byte[1024];

                System.out.println("Buscando servidores en la red...");

                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    try {
                        socket.receive(packet);
                        String data = new String(packet.getData(), 0, packet.getLength());

                        System.out.println("Servidor encontrado: " + data);


                    } catch (SocketTimeoutException e) {
                        System.out.println("No se encontraron más servidores.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
