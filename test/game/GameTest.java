package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void add() {
        Game game = new Game("Kiko");
        game.add("pesho");
        assertEquals(game.getPlayer2(), "pesho");
    }

    @Test
    void getPlayer1() {
    }

    @Test
    void isCurrentTurn() {
    }

    @Test
    void getJoinedCount() {
    }

    @Test
    void isFinished() {
    }

    @Test
    void getMyBoard() {
    }

    @Test
    void getEnemyBoard() {
    }
}