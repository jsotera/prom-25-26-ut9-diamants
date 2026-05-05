package edu.masanz.da.juegored.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Set;

public class ClientHandler extends Thread {

    private Socket socket;
    private Set<PrintWriter> clientWriters;

    public ClientHandler(Socket socket, Set<PrintWriter> clientWriters) {
        this.socket = socket;
        this.clientWriters = clientWriters;
    }

    public void run() {
        System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());
        PrintWriter out = null;
        Scanner in = null;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            synchronized (clientWriters) {
                clientWriters.add(out);
            }
            while (in.hasNextLine()) {
                String message = in.nextLine();
                System.out.println("Mensaje recibido: " + message);
                synchronized (clientWriters) {
                    for (PrintWriter writer : clientWriters) {
                        writer.println(message);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error en la conexión con un cliente.");
        } finally {
            if (out != null) {
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
            }
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

}
