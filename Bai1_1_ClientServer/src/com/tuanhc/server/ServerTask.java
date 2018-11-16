package com.tuanhc.server;

import java.io.*;
import java.net.Socket;

public class ServerTask extends Thread {
    private static final String nguyen_am = "ueoaiUEOAI";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerTask(Socket s) throws IOException {
        this.socket = s;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream()
        )), true);
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String line = in.readLine();
                if (line.equals("exit"))
                    break;
                System.out.println("Client: " + line);

                String strUpper = toUpperCase(line);
                out.println(strUpper);

                String strLower = toLowerCase(line);
                out.println(strLower);

                String strUpperLower = toUpperAndLower(line);
                out.println(strUpperLower);

                String nguyenAm = nguyenAm(line);
                out.println(nguyenAm);

                int countWord = countWord_2(line);
                out.println(String.valueOf(countWord));
            }
            System.out.println("Disconnect to client! " + socket);
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

    private String toUpperCase(String str) {
        String res = "";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z')
                c = (char)(c - 32);
            res += c;
        }
        return res;
    }

    private String toUpperAndLower(String str) {
        String res = "";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z')
                c = (char)(c - 32);
            else if (c >= 'A' && c <= 'Z')
                c = (char)(c + 32);
            res += c;
        }
        return res;
    }

    private String toLowerCase(String str) {
        String res = "";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z')
                c = (char)(c + 32);
            res += c;
        }
        return res;
    }

    private int countWord(String str) {
        int count = 0;
        //refine string
        str = str.trim().replaceAll(" +", " ");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                count++;
        }
        count++;
        return count;
    }

    public int countWord_2(String str) {
        str = " " + str + " ";
        int count = 0;
        boolean last_char_is_space = true;
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                last_char_is_space = true;
                continue;
            }

            if (last_char_is_space) {
                count++;
                last_char_is_space = false;
            }
        }
        return count;
    }

    private String nguyenAm(String str) {
        String res = "";
        for(int i = 0; i < str.length(); i++) {
            if (nguyen_am.indexOf(str.charAt(i)) != -1 && res.indexOf(str.charAt(i)) == -1)
                res += str.charAt(i) + " ";
        }

        return res;
    }
}
