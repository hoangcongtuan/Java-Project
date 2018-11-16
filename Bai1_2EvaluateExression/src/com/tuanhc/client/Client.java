package com.tuanhc.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;

    public static void main(String[] args) {
	// write your code here
        System.out.println("I am Client!");
        new Client();
    }

    public Client() {
        try {
            socket = new Socket("localhost", 8080);
            System.out.println("Connected to server!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connect to server failed!");
        }

        Thread inputThread = new Thread(() -> {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                while (true) {
                    String line = br.readLine();
                    System.out.println("Received: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        inputThread.run();

        Thread outputThread = new Thread(() -> {
            try {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String line = scanner.nextLine();
                    pw.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        outputThread.start();

    }
}
