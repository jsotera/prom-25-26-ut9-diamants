package edu.masanz.da.juegored.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

import static edu.masanz.da.juegored.core.Consts.PORT_UDP;

public class ServerManager {

    private static Set<PrintWriter> clientWriters = new HashSet<>();
    private static boolean aceptarJugadores = true;
    public static boolean servidorVivo = false;

    private static int puerto;
    private static String nombre;
    private static int jugadores;
    private static int maxJugadores;

    public static void lanzarServidor(int puerto, String nombre, int maxJugadores) {
        servidorVivo = true;
        ServerManager.puerto = puerto;
        ServerManager.nombre = nombre;
        jugadores = 1;
        ServerManager.maxJugadores = maxJugadores;
        System.out.println("Servidor iniciado en el puerto " + puerto);
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(puerto);
                while (servidorVivo) {
                    Socket socket = serverSocket.accept();
                    ClientHandler ch = new ClientHandler(socket, clientWriters);
                    ch.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        publicarServidor();
    }

    public static void publicarServidor() {
        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.setBroadcast(true);

                String message = InetAddress.getLocalHost().getHostAddress() + ";"+puerto+";"+nombre+";"+jugadores+";"+maxJugadores;
                byte[] buffer = message.getBytes();

                System.out.println("Servidor de descubrimiento iniciado...");

                while (servidorVivo && aceptarJugadores) {
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
        }).start();
    }


}
