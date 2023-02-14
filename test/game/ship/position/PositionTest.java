package game.ship.position;

import exception.InvalidPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    void TestConstructor() {
        try {
            Position pos = Position.of("O2");
        } catch (InvalidPositionException e) {
            assertEquals("Invalid position",e.getLocalizedMessage());
        }
    }

    @Test
    void TestOf() {
        assertThrows(InvalidPositionException.class, () -> Position.of("A22"));
    }

}