package game;

import game.board.Board;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {
    private boolean finished = false;
    private String player1 = null;
    private String player2 = null;
    private Board player1board;
    private Board player2board;
    private String currentTurn;

    public static Game of(String player1) {
        return Game.of(player1, new Board());
    }
    public static Game of(String player1, Board board1) {
        return new Game(player1, board1);
    }
    private Game(String player1) {
        this.player1 = player1;
        currentTurn = player1;
        this.player1board = new Board();
    }

    private Game(String player1, Board board1) {
        this.player1 = player1;
        currentTurn = player1;
        this.player1board = board1;
    }

    public void add(String player2) {
        add(player2, new Board());
    }
    public void add(String player2, Board board) {
        this.player2 = player2;
        this.player2board = board;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }


    public boolean isCurrentTurn(String name) {
        return currentTurn.equals(name);
    }

    public int getJoinedCount() {
        if (Objects.isNull(player2)) {
            return 1;
        }
        return 2;
    }

    public void endTurn() {
        if (currentTurn.equals(player1)) {
            currentTurn = player2;
        } else {
            currentTurn = player1;
        }
    }

    boolean isFinished() {
        return finished;
    }

    public Board getPlayer1board() {
        return player1board;
    }

    public Board getPlayer2board() {
        return player2board;
    }

    public Board getMyBoard(String name) {
        if (name.equals(player1)) {
            return player1board;
        } else if (name.equals(player2)) {
            return player2board;
        }
        return null;
    }

    public Board getEnemyBoard(String name) {
        if (!name.equals(player1)) {
            return player1board;
        } else if (!name.equals(player2)) {
            return player2board;
        }
        return null;
    }
}
