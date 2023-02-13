package game.ship;

import game.ship.position.Position;
import constants.Direction;
import constants.ShipType;

public class Carrier extends Ship {
    protected Carrier(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, size);
    }

    public char getSymbol() {
        return 'c';
    }
}
