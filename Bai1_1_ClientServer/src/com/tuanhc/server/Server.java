package com.tuanhc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final static int SERVER_PORT = 8080;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
       new Server();
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println(
                    "Server running at " + serverSocket.getLocalPort()
            );

            //wait for client
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client port: " + socket.getPort());
                try {
                    new ServerTask(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
