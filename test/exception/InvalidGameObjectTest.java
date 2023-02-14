package exception;

import game.BattleshipsAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidGameObjectTest {
    @Test
    public void throwInvalidGameObjectTest() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        battleshipsAPI.join("game0", "pesho");
        assertThrows(InvalidGameObject.class, () -> battleshipsAPI.getGameBoard("game0","gosho"));
    }
}