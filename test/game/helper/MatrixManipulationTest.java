package game.helper;

import constants.Direction;
import constants.ShipType;
import game.Player;
import game.board.Board;
import game.ship.Ship;
import game.ship.position.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixManipulationTest {

    @Test
    void getPrintableMatrix() {
        Board board = new Board();
        board.addShip(Ship.of(ShipType.CARRIER, Direction.HORIZONTAL, Position.of('A', 2)));
        //board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.HORIZONTAL, Position.of('A', 7)));
        board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.VERTICAL, Position.of('B', 2)));
        board.hit(Position.of("B2"));
        assertEquals("   0 1 2 3 4 5 6 7 8 9\n" +
                "A |-|-|-|-|-|-|-|-|-|-|\n" +
                "B |-|-|X|-|-|-|-|-|-|-|\n" +
                "C |-|-|-|-|-|-|-|-|-|-|\n" +
                "D |-|-|-|-|-|-|-|-|-|-|\n" +
                "E |-|-|-|-|-|-|-|-|-|-|\n" +
                "F |-|-|-|-|-|-|-|-|-|-|\n" +
                "G |-|-|-|-|-|-|-|-|-|-|\n" +
                "H |-|-|-|-|-|-|-|-|-|-|\n" +
                "I |-|-|-|-|-|-|-|-|-|-|\n" +
                "J |-|-|-|-|-|-|-|-|-|-|\n", MatrixManipulation.getPrintableMatrix(board, Player.ENEMY));
    }
}