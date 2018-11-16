package com.tuanhc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    public static void main(String []args) {
        new Server();
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Server running at port: " + serverSocket.getLocalPort());
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerTask(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
