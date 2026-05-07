package edu.masanz.da.juegored.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

import static edu.masanz.da.juegored.core.Consts.PORT_UDP;

public class ServerManager {

    public static Set<PrintWriter> clientWriters = new HashSet<>();
    public static Set<ClientHandler> clientes = new HashSet<>();
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

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(puerto);
                serverSocket.setSoTimeout(1000); // 1 segundo de espera máxima
                while (servidorVivo) {
                    try {
                        Socket socket = serverSocket.accept();
                        ClientHandler ch = new ClientHandler(socket);
                        clientes.add(ch);
                        ch.start();
                    } catch (SocketTimeoutException ste) {
                        // aqui entramos si en 1 segundo nadie ha aceptado la peticion
                    }
                }
                for (ClientHandler cliente : clientes) {
                    cliente.getSocket().close();
                }
                serverSocket.close();
                System.out.println("hilo lanzarServidor muerto");
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

                while (servidorVivo && aceptarJugadores) {
                    DatagramPacket packet = new DatagramPacket(
                            buffer, buffer.length,
                            InetAddress.getByName("255.255.255.255"), PORT_UDP
                    );
                    socket.send(packet);
                    Thread.sleep(2000);
                }
                socket.close();
                System.out.println("hilo publicarServidor muerto");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}
