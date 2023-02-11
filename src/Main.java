import files.FileType;
import game.Player;
import game.file.File;
import game.position.Position;
import game.board.Board;
import game.file.MatrixManipulation;
import game.ship.Ship;
import game.ship.constants.Direction;
import game.ship.constants.ShipType;

public class Main {
    public static void main(String[] args) {
        //System.out.println(Position.of("A2").getY());
        Board board = new Board();

        board.addShip(Ship.of(ShipType.CARRIER, Direction.HORIZONTAL, Position.of('A', 2)));
        //board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.HORIZONTAL, Position.of('A', 7)));
        board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.VERTICAL, Position.of('B', 2)));
        board.hit(Position.of("B2"));
        System.out.println(MatrixManipulation.getPrintableMatrix(board, Player.ME));
        System.out.println(File.getFile(FileType.LEGEND));
    }
}