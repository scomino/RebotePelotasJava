package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import pelotasjumi.Jugador;

public class SocketServer implements Runnable {
    private static final int PORT = 3005;
    private static HashSet<String> names = new HashSet<>();
    private static HashSet<PrintWriter> writers = new HashSet<>();

    static Jugador player;

    public SocketServer(Jugador player) {
        SocketServer.player = player;
    }

    @Override
    public void run() {
        ServerSocket cliente;
        try {
            cliente = new ServerSocket(PORT);
            System.out.println("Servidor a la escucha @"
                    + Inet4Address.getLocalHost().getHostAddress() + ":"
                    + PORT);
            try {
                while (true) {
                    new Handler(cliente.accept()).start();
                }
            } finally {
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
