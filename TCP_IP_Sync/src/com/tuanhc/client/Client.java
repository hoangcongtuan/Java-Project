package com.tuanhc.client;

import com.tuanhc.server.Server;

import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket socket;

    public Client() {
        try {
            socket = new Socket("localhost", Server.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
