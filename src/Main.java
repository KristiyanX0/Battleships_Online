import game.Position.Position;
import game.ship.Ship;
import game.ship.constants.Direction;
import game.ship.constants.ShipType;

public class Main {
    public static void main(String[] args) {
        System.out.println(Position.of("A2").getY());
        Ship ship = Ship.of(ShipType.CARRIER, Direction.HORIZONTAL, Position.of('A', 2));
        System.out.println(ship);
    }
}