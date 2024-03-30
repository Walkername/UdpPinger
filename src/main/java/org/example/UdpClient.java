package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class UdpClient {
    public static void main(String[] args) throws Exception {
        startClient(8);
    }

    private static void startClient(int pingsNumber) throws Exception {
        Socket socket = new Socket("127.0.0.1", 1111);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        for (int i = 1; i <= pingsNumber; i++) {
            try {
                long startTime = System.currentTimeMillis();

                String message = "Ping " + i + " " + startTime;
                out.println(message);

                socket.setSoTimeout(4000);

                String response = in.readLine();

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                System.out.println("Ping " + i + " time = " + elapsedTime + "ms");
            }
            catch (SocketTimeoutException e) {
                System.out.println("Request timed out");
            }
        }

        out.close();
        in.close();
        socket.close();
    }
}
