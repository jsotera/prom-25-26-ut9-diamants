package edu.masanz.da.juegored.server;

import edu.masanz.da.juegored.client.model.Jugador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static edu.masanz.da.juegored.core.Consts.*;
import static edu.masanz.da.juegored.server.ServerManager.clientWriters;
import static edu.masanz.da.juegored.server.ServerManager.clientes;

public class ClientHandler extends Thread {

    private Socket socket;
    private Jugador jugador;

    public ClientHandler(Socket socket) {
        this.socket = socket;
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
                if(message.startsWith(KEY_NEW_PLAYER)){
                    this.jugador = new Jugador(message.substring(KEY_NEW_PLAYER.length()+1), false);
                    Thread.sleep(500); // para darle tiempo a que cargue la vista del cliente
                    synchronized (clientWriters) {
                        String msgUsers = "";
                        for (ClientHandler ch : clientes) {
                            if(ch.jugador != null) {
                                msgUsers = msgUsers + ch.jugador.getNombreUsuario() + "---" + ch.jugador.isEstaListo()+";";
                            }
                        }
                        for (PrintWriter writer : clientWriters) {
                            writer.println(KEY_WAITING_USERS+":"+msgUsers);
                        }
                    }
                } else if (message.startsWith(KEY_USER_EXIT)) {
                    synchronized (clientWriters) {
                        String msgUsers = "";
                        for (ClientHandler ch : clientes) {
                            if(ch.jugador != null && !ch.jugador.getNombreUsuario().equals(message.substring(KEY_USER_EXIT.length()+1))) {
                                msgUsers = msgUsers + ch.jugador.getNombreUsuario() + "---" + ch.jugador.isEstaListo()+";";
                            }
                        }
                        for (PrintWriter writer : clientWriters) {
                            writer.println(KEY_WAITING_USERS+":"+msgUsers);
                        }
                    }
                }
            }
            System.out.println("hilo ClientHandler muerto ");
        } catch (IOException e) {
            System.out.println("Error en la conexión con un cliente.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                synchronized (clientWriters) {
                    clientes.remove(this);
                    clientWriters.remove(out);
                }
            }
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
