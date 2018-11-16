package com.tuanhc.server;

import java.io.*;
import java.net.Socket;


public class ServerTask extends Thread {

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public ServerTask(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = in.readLine();
                if(line.equals("exit"))
                    break;
                System.out.println("Client: " + line);
                Float result = Evaluation.getInstance().calculate(line);
                out.println(String.valueOf(result));

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
