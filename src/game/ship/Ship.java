package game.ship;

import exception.InvalidShipTypeException;
import game.Position.Position;
import game.ship.constants.Direction;
import game.ship.constants.ShipSize;
import game.ship.constants.ShipType;

public abstract class Ship {
    private final Direction DIRECTION;
    private final Position POSITION;
    private final int SIZE;
    private final ShipType SHIP_TYPE;

    public static Ship of(ShipType shipType, Direction direction, Position position) {
        return switch (shipType) {
            case CARRIER -> new Carrier(shipType, direction, position, ShipSize.CARRIER);
            case BATTLESHIP -> new Battleship(shipType, direction, position, ShipSize.BATTLESHIP);
            case DESTROYER -> new Destroyer(shipType, direction, position, ShipSize.DESTROYER);
            case SUBMARINE -> new Submarine(shipType, direction, position, ShipSize.SUBMARINE);
//            default -> throw new InvalidShipTypeException(String.format("Ship with the name %s doesn't exist",
//                    shipType.toString()));
        };
    }


    public Ship(ShipType shipType, Direction DIRECTION, Position POSITION, int size) {
        this.SHIP_TYPE = shipType;
        this.DIRECTION = DIRECTION;
        this.POSITION = POSITION;
        this.SIZE = size;
    }

    public int size() {
        return SIZE;
    }

    public int type() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "DIRECTION=" + DIRECTION +
                ", POSITION=" + POSITION +
                ", SIZE=" + SIZE +
                ", SHIP_TYPE=" + SHIP_TYPE +
                '}';
    }
}
