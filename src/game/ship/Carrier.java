package game.ship;

import game.Position.Position;
import game.ship.constants.Direction;
import game.ship.constants.ShipType;

public class Carrier extends Ship {
    protected Carrier(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, 5);
    }
}
