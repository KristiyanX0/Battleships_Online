package exception;

import game.BattleshipsAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamePlayerAlredyDefinedTest {
    @Test
    public void throwGGamePlayerAlredyDefinedTest() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        assertThrows(GamePlayerAlredyDefined.class, () ->
                battleshipsAPI.join("game0", "kiko"));
    }
}