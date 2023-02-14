package game.ship;

import exception.InvalidMappingException;
import exception.InvalidShipTypeException;
import game.ship.position.Position;
import game.ship.position.PositionState;
import constants.Direction;
import constants.ShipSize;
import constants.ShipType;

import java.util.*;

public abstract class Ship {
    private final Direction DIRECTION;
    private final HashMap<Position, PositionState> positions = new HashMap<>();
    private final int SIZE;
    private final ShipType SHIP_TYPE;

    public static Ship of(ShipType shipType, Direction direction, Position positionStart) {
        return switch (shipType) {
            case CARRIER -> new Carrier(shipType, direction, positionStart, ShipSize.CARRIER);
            case BATTLESHIP -> new Battleship(shipType, direction, positionStart, ShipSize.BATTLESHIP);
            case DESTROYER -> new Destroyer(shipType, direction, positionStart, ShipSize.DESTROYER);
            case SUBMARINE -> new Submarine(shipType, direction, positionStart, ShipSize.SUBMARINE);
//            default -> throw new InvalidShipTypeException(String.format("Ship with the name %s doesn't exist",
//                    shipType.toString()));
        };
    }

    public static Ship of(String shipType, String direction, String positionStart) {
        ShipType shipType1 = switch (shipType) {
            case "b" -> ShipType.BATTLESHIP;
            case "c" -> ShipType.CARRIER;
            case "d" -> ShipType.DESTROYER;
            case "s" -> ShipType.SUBMARINE;
            default -> throw new InvalidShipTypeException();
        };
        Direction direction1 = switch (direction) {
            case "h" -> Direction.HORIZONTAL;
            case "v" -> Direction.VERTICAL;
            default -> throw new RuntimeException();
        };
        return of(shipType1, direction1, Position.of(positionStart));
    }

    protected Ship(ShipType shipType, Direction DIRECTION, Position POSITION_START, int size) {
        this.SHIP_TYPE = shipType;
        this.DIRECTION = DIRECTION;
        this.SIZE = size;

        positions.putIfAbsent(POSITION_START, PositionState.UNDAMAGED);
        if (DIRECTION == Direction.HORIZONTAL) {
            for (int i = 1; i < size; ++i) {
                positions.putIfAbsent(Position.of(POSITION_START.getX(), POSITION_START.getY() + i), PositionState.UNDAMAGED);
            }
        } else {
            for (int i = 1; i < size; ++i) {
                positions.putIfAbsent(Position.of(POSITION_START.getX() + i, POSITION_START.getY()), PositionState.UNDAMAGED);
            }
        }
    }

    public PositionState positionState(Position pos) {
        if (!positions.containsKey(pos) || positions.get(pos) == PositionState.EMPTY) {
            throw new InvalidMappingException();
        }
        return positions.get(pos);
    }

    /**
     * @param pos position
     * @return TRUE - currently hit
     *         FALSE - already hit
     */
    public boolean setDamage(Position pos) {
        if (positions.get(pos) == PositionState.UNDAMAGED) {
            positions.put(pos, PositionState.DAMAGED);
            return true;
        }
        return false;
    }

    public boolean setDamage(int x, int y) {
        return setDamage(Position.of(x,y));
    }

    public Set<Position> getPositions() {
        return Collections.unmodifiableSet(positions.keySet());
    }

    public int size() {
        return SIZE;
    }

    public ShipType type() {
        return SHIP_TYPE;
    }

    public abstract char getSymbol();

    @Override
    public String toString() {
        return "Ship{" +
                "DIRECTION=" + DIRECTION +
                ", SIZE=" + SIZE +
                ", SHIP_TYPE=" + SHIP_TYPE +
                '}';
    }
}
