package game.board;

import exception.ShipIntersectionException;
import game.ship.position.Position;
import game.ship.Ship;
import operations.SetOperation;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Board implements Serializable {
    public final static int SIZE = 10;
    private final HashMap<Position, Ship> ships = new HashMap<>();

    Matrix matrix = new Matrix();

    public void addShip(Ship ship) {
        if (!SetOperation.intersection(ship.getPositions(), ships.keySet()).isEmpty()) {
            throw new ShipIntersectionException();
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

    /**
     * @return unmodifiable positions set
     */
    public Set<Position> positions() {
        return Collections.unmodifiableSet(ships.keySet());
    }

    public Matrix getMatrix() {
        return matrix;
    }
}
