package ru.netology;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8085;

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        while (true) {
            try (final ServerSocket serverSocket = new ServerSocket(PORT);
                 final Socket clientSocket = serverSocket.accept(); // ждем подключения
                 final PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                System.out.println("New connection accepted");

                final String name = in.readLine();

                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
