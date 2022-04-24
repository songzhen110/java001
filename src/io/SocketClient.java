package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.TreeSet;

public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.31.140",9098);
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                String s = bufferedReader.readLine();
                if(s!=null){
                    outputStream.write(s.getBytes(StandardCharsets.UTF_8));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
