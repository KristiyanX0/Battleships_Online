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

    private static Set<Position> unavaiablePos(Board board, String dir, int size) {
        Set<Position> pos = new HashSet<>();
        for (var p : board.positions()) {
            if (dir.equals("h")) {
                pos.addAll(getPos(Position.of(p.getX(), Math.max(p.getY() - size + 1, 0)), p));
            } else {
                pos.addAll(getPos(Position.of(Math.max(p.getX() - size + 1, 0), p.getY()), p));
            }
        }
        return pos;
    }

    private static Set<Position> getPos(Position pos1, Position pos2) {
        Set<Position> pos = new HashSet<>();
        if (pos1.getX() == pos2.getX()) {
            Position position = pos1;
            while (position.equals(pos2)) {
                pos.add(position);
                position = Position.of(position.getX(), position.getY() + 1);
            }
            pos.add(position);
        } else if (pos1.getY() == pos2.getY()) {
            Position position = pos1;
            while (position.equals(pos2)) {
                pos.add(position);
                position = Position.of(position.getX() + 1, position.getY());
            }
            pos.add(position);
        }
        return pos;
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
        Set<Position> firstSub = SetOperation.subtract(pos, unavaiablePos(board,dir, size));
        return SetOperation.subtract(firstSub, board.positions());
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
