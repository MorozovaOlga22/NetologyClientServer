package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final String host = "netology.homework";
        try (final Socket clientSocket = new Socket(host, Server.PORT);
             final PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             final Scanner scanner = new Scanner(System.in)
        ) {
            while (true) {
                final String serverResponse = in.readLine();
                System.out.println(serverResponse);
                if (serverResponse.endsWith("Bye!")) {
                    return;
                }
                out.println(scanner.nextLine());
            }
        }
    }
}
