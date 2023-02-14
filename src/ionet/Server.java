package ionet;

import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket player1;
    private Socket player2;

    public void start() {
        try {
            // Listen for connections on port 8000
            serverSocket = new ServerSocket(8000);
            System.out.println("Server started, waiting for players to connect...");

            // Wait for player 1 to connect
            player1 = serverSocket.accept();
            System.out.println("Player 1 connected: " + player1.getInetAddress());

            // Wait for player 2 to connect
            player2 = serverSocket.accept();
            System.out.println("Player 2 connected: " + player2.getInetAddress());

            // Start the game for player 1 and player 2
            startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame() {
        // ... implement the game logic
    }
}