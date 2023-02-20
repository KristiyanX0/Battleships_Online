package game.helper;

import game.Player;
import game.board.Board;
import game.board.Matrix;

public class MatrixManipulation {
    private static final String FIRST_ROW = "   0 1 2 3 4 5 6 7 8 9";

    public static String getPrintableMatrix(Board board, Player player) {
        Matrix matrix = board.getMatrix();
        StringBuilder output = new StringBuilder();
        output.append(FIRST_ROW).append(System.lineSeparator());
        for (int i = 0; i < Board.SIZE; ++i) {
            StringBuilder str = new StringBuilder();
            str.append((char) ('A' + i)).append(' ');
            for (int j = 0; j < Board.SIZE; ++j) {
                str.append('|');
                if (player == Player.ME) {
                    char symbol;
                    if (matrix.get(i, j) == Matrix.DAMAGE) {
                        symbol = Matrix.DAMAGE;
                    } else if (matrix.get(i, j) == Matrix.EMPTY_HIT) {
                        symbol = Matrix.EMPTY;
                    } else {
                        symbol = matrix.get(i, j);
                    }
                    str.append(symbol);
                } else {
                    char symbol;
                    if (matrix.get(i, j) == Matrix.DAMAGE) {
                        symbol = Matrix.DAMAGE;
                    } else if (matrix.get(i, j) == Matrix.EMPTY_HIT) {
                        symbol = Matrix.EMPTY_HIT;
                    } else {
                        symbol = Matrix.EMPTY;
                    }
                    str.append(symbol);
                }
            }
            str.append('|').append(System.lineSeparator());
            output.append(str);
        }
        return output.toString();
    }

}
