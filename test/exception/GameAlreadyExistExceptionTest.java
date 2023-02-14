package exception;

import constants.Direction;
import constants.ShipType;
import game.BattleshipsAPI;
import game.ship.Ship;
import game.ship.position.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameAlreadyExistExceptionTest {
    @Test
    public void throwGameAlreadyExistExceptionTest() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        assertThrows(GameAlreadyExistException.class, () -> battleshipsAPI.createGame("game0", "kiko"));
        String expectedMessage = "Invalid input";
    }
}