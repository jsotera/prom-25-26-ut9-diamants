package edu.masanz.da.juegored.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

import static edu.masanz.da.juegored.core.Consts.PORT_UDP;

public class ServerManager {

    private static Set<PrintWriter> clientWriters = new HashSet<>();
    private static boolean aceptarJugadores;


    public static void startServer() {
        aceptarJugadores = true;
        int puerto = (int)(Math.random()*20000)+10000; // 10000 - 29999
        System.out.println("Servidor iniciado en el puerto " + puerto);
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler ch = new ClientHandler(socket, clientWriters);
                ch.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void publicarServidor() {
        aceptarJugadores = true;
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);

            // Los datos que quieres que el cliente vea antes de conectar
            String message = "IP:" + InetAddress.getLocalHost().getHostAddress() + ";PORT:5000;NAME:Partida_Pro";
            byte[] buffer = message.getBytes();

            System.out.println("Servidor de descubrimiento iniciado...");

            while (aceptarJugadores) {
                DatagramPacket packet = new DatagramPacket(
                        buffer, buffer.length,
                        InetAddress.getByName("255.255.255.255"), PORT_UDP
                );
                socket.send(packet);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
