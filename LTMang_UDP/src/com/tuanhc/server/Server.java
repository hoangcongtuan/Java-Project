package com.tuanhc.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server {
    public static final int SERVER_PORT = 8080;
    DatagramSocket serverSocket;
    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 10/24/18 Handle Exception
            System.out.println("Loi khi tao server!!");
        }
    }

    public Server() throws IOException {
        serverSocket = new DatagramSocket(SERVER_PORT);
        System.out.println("Server started!");
        while(true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String request = new String(receivePacket.getData());
            System.out.println("Client: " + request);

            //get time
            Calendar calendar = Calendar.getInstance();
            String strDate = calendar.getTime().toString();
            System.out.println("Server: " + strDate);

            byte[] sendData = new byte[1024];
            sendData = strDate.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);
        }
    }
}
