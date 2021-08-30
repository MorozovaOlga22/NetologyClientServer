package ru.netology;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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

                final StringBuilder resultResponse = new StringBuilder("Hello, ");
                out.println("Enter name");
                resultResponse.append(in.readLine());
                out.println("Enter your country");
                resultResponse.append(" from ").append(in.readLine()).append("! ");
                out.println("Choose number of your goal: " +
                        "1. Get date. " +
                        "2. Find information. " +
                        "3. Check server.");

                switch (in.readLine()) {
                    case "1": {
                        resultResponse.append("Today is ").append(new Date());
                        break;
                    }
                    case "2": {
                        out.println("Enter your request");
                        resultResponse.append("Thank you! We will find information about '").append(in.readLine()).append("'");
                        break;
                    }
                    case "3": {
                        resultResponse.append("Server works! Please, stop asking this!");
                        break;
                    }
                    default: {
                        resultResponse.append("Incorrect input!");
                    }
                }
                resultResponse.append(" Bye!");
                out.println(resultResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
