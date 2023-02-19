package game.board;

import exception.ShipAlreadyExistException;
import game.ship.position.Position;
import game.ship.Ship;
import operations.SetOperation;

import java.io.Serializable;
import java.util.HashMap;

public class Board implements Serializable {
    public final static int SIZE = 10;
    private final HashMap<Position, Ship> ships = new HashMap<>();

    Matrix matrix = new Matrix();

    public void addShip(Ship ship) {
        if (!SetOperation.intersection(ship.getPositions(), ships.keySet()).isEmpty()) {
            throw new ShipAlreadyExistException();
        } else {
            for (var i : ship.getPositions()) {
                ships.put(i, ship);
            }
        }
        matrix.update(ships);
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
        boolean output = ships.get(pos).setDamage(pos);
        matrix.update(ships);
        return output;
    }

    public boolean hit(String str) {
        return hit(Position.of(str));
    }

    public Matrix getMatrix() {
        return matrix;
    }
}
