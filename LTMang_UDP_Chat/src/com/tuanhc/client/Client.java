package com.tuanhc.client;

import com.tuanhc.server.Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    DatagramSocket socket;
    public static void main(String[] args) {
        try {
            new Client();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public Client() throws SocketException {
        socket = new DatagramSocket();
        new InputThread(socket);
        new OutputThread(socket);
        System.out.println("Client Started!");
    }

    class InputThread extends Thread {
        DatagramSocket socket;
        public InputThread(DatagramSocket socket) {
            this.socket = socket;
            start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    byte[] data = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(data, data.length);
                    this.socket.receive(dp);

                    String strData = new String(dp.getData());
                    strData = strData.trim();
                    System.out.println("Server: " + strData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class OutputThread extends Thread {
        DatagramSocket socket;
        Scanner scanner = new Scanner(System.in);
        public OutputThread(DatagramSocket socket) {
            this.socket = socket;
            start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    String line = scanner.nextLine();

                    byte[] data = line.getBytes();
                    DatagramPacket dp = new DatagramPacket(data, data.length);
                    dp.setPort(Server.SERVER_PORT);
                    dp.setAddress(InetAddress.getByName("localhost"));
                    socket.send(dp);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
