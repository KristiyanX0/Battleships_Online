package game.board;

import exception.ShipAlreadyExistException;
import game.Position.Position;
import game.ship.Ship;
import operations.SetOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Board {
    public static int SIZE = 10;
    private final HashMap<Position, Ship> ships = new HashMap<>();
    private final ArrayList<ArrayList<Integer>> board = new ArrayList<>(SIZE);

    public Board() {
        board.addAll(new ArrayList<>(SIZE));
    }

    public void addShip(Ship ship) {
        if (!SetOperations.intersection(ship.getPositions(), ships.keySet()).isEmpty()) {
            throw new ShipAlreadyExistException();
        } else {
            for (var i : ship.getPositions()) {
                ships.put(i, ship);
            }
        }
    }

    /**
     * @param pos position
     * @return TRUE - currently hit
     *
     *         FALSE - already hit or empty position
     */
    public boolean hit(Position pos) {
        if (!ships.containsKey(pos)) {
            return false;
        }
        return ships.get(pos).setDamage(pos);
    }
}
