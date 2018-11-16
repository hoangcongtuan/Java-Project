package com.tuanhc.client;

import com.tuanhc.server.Server;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    DatagramSocket clientSocket;
    public static void main(String[] args) {
        try {
            new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client() throws IOException {
        clientSocket = new DatagramSocket();
        System.out.println("Client Started!!");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Client: ");
            String line = scanner.nextLine();
            if (line.equals("exit"))
                break;

            byte[] sendData = line.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length);
            sendPacket.setPort(Server.SERVER_PORT);
            sendPacket.setAddress(InetAddress.getByName("localhost"));
            clientSocket.send(sendPacket);


            //received data
            byte[] receivedData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivePacket);
            String receiveString = new String(receivedData, "ASCII");
            System.out.println("Server: " + receiveString);

        }
    }
}
