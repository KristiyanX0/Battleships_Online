package game.ship;

import game.Position.Position;
import game.ship.constants.Direction;
import game.ship.constants.ShipType;

public class Submarine extends Ship {
    protected Submarine(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, size);
    }
}
