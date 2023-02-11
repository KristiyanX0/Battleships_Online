package game.ship;

import game.position.Position;
import game.ship.constants.Direction;
import game.ship.constants.ShipType;

public class Carrier extends Ship {
    protected Carrier(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, size);
    }

    public char getSymbol() {
        return 'c';
    }
}
