package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        final String host = "localhost";
        try (final Socket clientSocket = new Socket(host, Server.PORT);
             final PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            out.println("our best client ever");
            final String resp = in.readLine();
            System.out.println(resp);
        }
    }
}
