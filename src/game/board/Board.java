package game.board;

import java.util.ArrayList;

public class Board {
    public static int SIZE = 10;
    private final ArrayList<ArrayList<Integer>> board = new ArrayList<>(SIZE);

    public Board() {
        board.addAll(new ArrayList<>(SIZE));
    }

//    public addShip() {
//
//    }
}
