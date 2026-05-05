package edu.masanz.da.juegored.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import static edu.masanz.da.juegored.core.Consts.PORT;

public class ServerManager {

    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void startServer() {
        System.out.println("Servidor iniciado en el puerto " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler ch = new ClientHandler(socket, clientWriters);
                ch.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
