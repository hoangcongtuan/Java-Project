package com.tuanhc.client;

import com.tuanhc.server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;

    public static void main(String[] args) {
        new Client();
    }

    private Client() {
        init();
        try {
            initInputOutputThread();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Co loi khi khoi tao luong vao ra!");
        }
    }

    private void initInputOutputThread() throws IOException {
        InputThread inputThread = new InputThread(clientSocket.getInputStream());
        inputThread.start();

        OutputThread outputThread = new OutputThread(clientSocket.getOutputStream());
        outputThread.start();

    }

    private void init() {
        clientSocket = null;
        try {
            clientSocket = new Socket("localhost", Server.SERVER_PORT);
            System.out.println("Connect to server at port: " + clientSocket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class InputThread extends Thread {
    private InputStream inputStream;
    private BufferedReader br;

    InputThread(InputStream inputStream) {
        this.inputStream = inputStream;
        br = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void run() {
        while (true) {
            String line;
            try {
                line = br.readLine();
                System.out.println("Server: " + line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class OutputThread extends Thread {
    private OutputStream outputStream;
    private PrintWriter pw;
    private Scanner scanner = new Scanner(System.in);

    public OutputThread(OutputStream outputStream) {
        this.outputStream = outputStream;
        pw = new PrintWriter(outputStream, true);
    }

    @Override
    public void run() {
        while (true) {
            System.out.print("Client: ");
            String strInput = scanner.nextLine();
            pw.println(strInput);
        }
    }
}


