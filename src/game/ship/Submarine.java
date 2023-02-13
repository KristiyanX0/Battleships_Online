package game.ship;

import game.ship.position.Position;
import constants.Direction;
import constants.ShipType;

public class Submarine extends Ship {
    protected Submarine(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, size);
    }

    public char getSymbol() {
        return 's';
    }
}
