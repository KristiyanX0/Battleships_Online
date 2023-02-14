package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipsAPITest {

    @Test
    void join() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        battleshipsAPI.join("game0", "pesho");
        assertEquals(battleshipsAPI.getGameBoard("game0", "pesho").getPlayer2(), "pesho");
    }

    @Test
    void getGameBoard() {

    }

    @Test
    void removeGame() {
    }

    @Test
    void list() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        assertEquals(battleshipsAPI.list().toString(), "1: Game - game0, Creator: kiko, Joined Count: 1\n");
    }
}