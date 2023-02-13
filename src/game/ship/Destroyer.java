package game.ship;

import game.ship.position.Position;
import constants.Direction;
import constants.ShipType;

public class Destroyer extends Ship {
    protected Destroyer(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, size);
    }

    public char getSymbol() {
        return 'd';
    }
}
