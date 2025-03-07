package game.board;

import game.position.Position;
import game.position.PositionState;
import game.ship.Ship;

import java.util.HashMap;

public class Matrix {

    public static final char EMPTY = '-';
    public static final char DAMAGE = 'X';
    private char[][] board = new char[Board.SIZE][Board.SIZE];

    public Matrix() {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void update(HashMap<Position, Ship> ships) {
        for (var i : ships.entrySet()) {
            Position pos = i.getKey();
            Ship ship = i.getValue();
            PositionState positionState = ship.positionState(pos);
            if (positionState == PositionState.DAMAGED) {
                board[pos.getX()][pos.getY()] = DAMAGE;
            } else if (positionState == PositionState.UNDAMAGED) {
                board[pos.getX()][pos.getY()] = ship.getSymbol();
                ship.type();
            } else {
                board[pos.getX()][pos.getY()] = EMPTY;
            }
        }
    }

    public char get(int i, int y) {
        return board[i][y];
    }

    public char get(Position pos) {
        return get(pos.getX(), pos.getY());
    }
}
