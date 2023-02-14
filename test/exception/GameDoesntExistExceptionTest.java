package exception;

import game.BattleshipsAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDoesntExistExceptionTest {
    @Test
    public void throwGameDoesntExistExceptionTest() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        assertThrows(GameDoesntExistException.class, () ->
                battleshipsAPI.join("game0", "kiko"));
    }
}