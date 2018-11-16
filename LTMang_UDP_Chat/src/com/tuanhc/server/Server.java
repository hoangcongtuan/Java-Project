package com.tuanhc.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public final static int SERVER_PORT = 8080;
    int clientPort;
    InetAddress clientAddress;
    DatagramSocket socket;

    public static void main(String[] args) {
        try {
            new Server();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() throws IOException {
        socket = new DatagramSocket(SERVER_PORT);
        //wait for the first message
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("Server started, wait for first message!!");
        socket.receive(receivePacket);
        this.clientPort = receivePacket.getPort();
        this.clientAddress = receivePacket.getAddress();
        String firstMessage = new String(receivePacket.getData());
        firstMessage = firstMessage.trim();
        System.out.println("Client connected, first message: " + firstMessage);
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

                    String strData = new String(dp.getData());
                    strData = strData.trim();
                    System.out.println("Client: " + strData);
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
