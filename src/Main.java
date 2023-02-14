
import game.BattleshipsAPI;
import game.BattleshipsAPIUtils;
import game.Game;

public class Main {
    public static void main(String[] args) {

//        board.addShip(Ship.of(ShipType.CARRIER, Direction.HORIZONTAL, Position.of('A', 2)));
//        //board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.HORIZONTAL, Position.of('A', 7)));
//        board.addShip(Ship.of(ShipType.BATTLESHIP, Direction.VERTICAL, Position.of('B', 2)));
//        board.hit(Position.of("B2"));
//        System.out.println(MatrixManipulation.getPrintableMatrix(board, Player.ME));


        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
//        battleshipsAPI.createProfile("Kiko", "1234");
//        battleshipsAPI.createProfile("Pesho", "4321");
//        Profile kiko = battleshipsAPI.login("Kiko", "1234");
//        Profile pesho = battleshipsAPI.login("Kiko", "1234");
//        kiko.createGame("Game0");
//        Game game = kiko.join("Game0");
//        game.getPlayer1board().hit("A2");


        BattleshipsAPIUtils.saveToFile(battleshipsAPI, "./src/files/game/Game0.txt");

    }
}