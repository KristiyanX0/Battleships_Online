package game.helper;

import constants.ShipSize;
import game.board.Board;
import game.ship.Ship;
import game.ship.position.Position;
import operations.SetOperation;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Randomise {
    private static String getRandomDirection() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return "v";
        } else {
            return "h";
        }
    }

    private static boolean unavaiablePos() {

    }
    private static Set<Position> getUniversum(Board board, String dir, int size) {
        Set<Position> pos = new HashSet<>();
        int sizeH = 0, sizeV = 0;
        if (dir.equals("v")) {
            sizeV = size;
        } else {
            sizeH = size;
        }

        for (char i = 'A'; i <= 'J' - sizeV; ++i) {
            for (int j = 0; j < 10 - sizeH; ++j) {
                pos.add(Position.of(i, j));
            }
        }
        Set<Position> originalUniversum = SetOperation.subtract(pos, board.positions());
        return SetOperation.subtract(originalUniversum, );
    }

    private static String getRandomPlace(Board board, String dir, int size) {
        Random rand = new Random();
        Set<Position> pos = getUniversum(board, dir, size);
        Position position = (Position) pos.toArray()[rand.nextInt(0, pos.size())];
        return position.getPos();
    }

    public static Board randomisedBoard() {
        Board board = new Board();
        String dir = Randomise.getRandomDirection();
        board.addShip(Ship.of("c", dir, Randomise.getRandomPlace(board, dir, ShipSize.CARRIER)));
        dir = Randomise.getRandomDirection();
        board.addShip(Ship.of("b", dir, Randomise.getRandomPlace(board, dir, ShipSize.BATTLESHIP)));
        dir = Randomise.getRandomDirection();
        board.addShip(Ship.of("d", dir, Randomise.getRandomPlace(board, dir, ShipSize.DESTROYER)));
        dir = Randomise.getRandomDirection();
        board.addShip(Ship.of("s", dir, Randomise.getRandomPlace(board, dir, ShipSize.SUBMARINE)));
        dir = Randomise.getRandomDirection();
        board.addShip(Ship.of("s", dir, Randomise.getRandomPlace(board, dir, ShipSize.SUBMARINE)));
        return board;
    }
}
