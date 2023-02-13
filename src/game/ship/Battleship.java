package game.ship;

import game.ship.position.Position;
import constants.Direction;
import constants.ShipType;

public class Battleship extends Ship {
    protected Battleship(ShipType shipType, Direction direction, Position position, int size) {
        super(shipType, direction, position, size);
    }
    public char getSymbol() {
        return 'b';
    }
}
