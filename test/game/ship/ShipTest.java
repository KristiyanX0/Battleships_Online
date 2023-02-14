package game.ship;

import constants.Direction;
import constants.ShipType;
import game.ship.position.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void of() {
        assertEquals(Ship.of("b","v","B2").toString(),
                Ship.of(ShipType.BATTLESHIP, Direction.VERTICAL, Position.of('B', 2)).toString());
    }
}