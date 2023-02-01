package game.ship;

import game.Position.Position;
import game.ship.constants.Direction;
import game.ship.constants.ShipType;

public class Destroyer extends Ship {
    protected Destroyer(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, size);
    }
}
