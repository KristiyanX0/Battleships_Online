package game.board;

import constants.Direction;
import constants.ShipType;
import game.ship.Ship;
import game.ship.position.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testAddShip() {
        Board board = new Board();
        board.addShip(Ship.of(ShipType.CARRIER, Direction.HORIZONTAL, Position.of('A', 2)));
        //board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.HORIZONTAL, Position.of('A', 7)));
        board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.VERTICAL, Position.of('B', 2)));
        assertEquals(board.getMatrix().get('A',2),'c');
    }

    @Test
    void testHit() {
        Board board = new Board();
        board.addShip(Ship.of(ShipType.CARRIER, Direction.HORIZONTAL, Position.of('A', 2)));
        //board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.HORIZONTAL, Position.of('A', 7)));
        board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.VERTICAL, Position.of('B', 2)));
        board.addShip(Ship.of(ShipType.SUBMARINE, Direction.VERTICAL, Position.of('C', 3)));
        board.addShip(Ship.of(ShipType.DESTROYER, Direction.VERTICAL, Position.of('D', 5)));
        board.hit(Position.of("B2"));
        assertEquals(board.getMatrix().get('B', 2), 'X');
    }
}