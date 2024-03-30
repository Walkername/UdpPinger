package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UdpServer {
    public static void main(String[] args) throws Exception {
        startServer();
    }

    private static void startServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(1111);
        Socket socket = serverSocket.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            int randNum = (int) (Math.random() * 10);

            String message = in.readLine();
            if (message == null) {
                break;
            }
            System.out.println("Message from client: " + message);

            if (randNum > 4) {
                continue;
            }
            out.println("message");
        }
        in.close();
        out.close();
        serverSocket.close();
    }
}