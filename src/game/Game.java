package game;

import game.board.Board;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Game implements Serializable {
    private String player1 = null;
    private String player2 = null;
    private final Board player1board = new Board();
    private final Board player2board = new Board();

    private String currentTurn;
    public Game(String player1) {
        this.player1 = player1;
        currentTurn = player1;
    }

    public void add(String player2) {
        this.player2 = player2;
    }

    public void saveToFile(String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, this.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Board getPlayer1board() {
        if (currentTurn.equals(player1)) {
            return player1board;
        } else {
            return player2board;
        }
    }

    public boolean isCurrentTurn(String name) {
        return currentTurn.equals(name);
    }

    public void endTurn() {
        if (currentTurn.equals(player1)) {
            currentTurn = player2;
        } else {
            currentTurn = player1;
        }
    }
}
