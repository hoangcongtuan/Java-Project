package com.tuanhc.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServerTask {
    int clientPort;
    InetAddress clientAddress;
    DatagramSocket socket;
    public ServerTask(int clientPort, InetAddress clientAddress, DatagramSocket socket) {
        this.clientPort = clientPort;
        this.socket = socket;
        this.clientAddress = clientAddress;
        new InputThread(socket, clientPort, clientAddress);
        new OutputThread(socket, clientPort, clientAddress);
    }

    class InputThread extends Thread {
        DatagramSocket socket;
        int clientPort;
        InetAddress clientAddress;
        public InputThread(DatagramSocket socket, int clientPort, InetAddress clientAddress) {
            this.socket = socket;
            this.clientPort = clientPort;
            this.clientAddress = clientAddress;
            start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    byte[] data = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(data, data.length);
                    this.socket.receive(dp);

                    String strData = new String(dp.getData(), "UTF-8");
                    System.out.println("Server: " + strData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class OutputThread extends Thread {
        DatagramSocket socket;
        int clientPort;
        InetAddress clientAddress;
        Scanner scanner = new Scanner(System.in);
        public OutputThread(DatagramSocket socket, int clientPort, InetAddress clientAddress) {
            this.socket = socket;
            this.clientPort = clientPort;
            this.clientAddress = clientAddress;
            start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    String line = scanner.nextLine();

                    byte[] data = line.getBytes();
                    DatagramPacket dp = new DatagramPacket(data, data.length);
                    dp.setPort(clientPort);
                    dp.setAddress(clientAddress);
                    socket.send(dp);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
