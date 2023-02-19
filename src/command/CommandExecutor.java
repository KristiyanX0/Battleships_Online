package command;

import exception.GameDoesntExistException;
import game.BattleshipsAPI;
import game.BattleshipsAPIUtils;
import game.Game;
import game.Player;
import game.command.FileCommand;
import game.file.MatrixManipulation;
import game.ship.Ship;

public class CommandExecutor {

    /* ===================== FIRST COMMAND ======================= */
    private static final String CREATE = "create";
    private static final String DELETE = "delete";
    private static final String SAVE = "save";
    private static final String LIST = "list";
    private static final String START = "start";
    private static final String JOIN = "join";
    private static final String PRINT = "print";
    /* =========================================================== */

    /* ========== SECOND COMMAND (DEPENDS ON FIRST ONE) ========== */
    private static final String GAME = "game";
    private static final String PROFILE = "profile";
    private static final String ADD = "add";
    /* =========================================================== */

    /* ===================== INGAME COMMAND ====================== */
    private static final String HIT = "hit";
    private static final String EXIT = "exit";
    /* =========================================================== */
    private static final String UNKNOWN_COMMAND = "Unknown command";
    public static final String DISCONNECTED = "Disconnected from the server";
    private final BattleshipsAPI game;

    Game gameplayed = null;
    private boolean inGame = false;
    public CommandExecutor(BattleshipsAPI game) {
        this.game = game;
    }
    public String execute(Command cmd) {
        if (this.inGame) {
            return switch (cmd.command()) {
                case ADD -> add(cmd.arguments());
                case HIT -> hit(cmd.arguments());
                case EXIT -> exitGame();
                case PRINT -> print(cmd.arguments());
                default -> UNKNOWN_COMMAND;
            };
        } else {
            return switch (cmd.command()) {
                case CREATE -> create(cmd.arguments());
                case DELETE -> delete(cmd.arguments());
                case SAVE -> save();
                case LIST -> list();
                case JOIN -> join(cmd.arguments());
                case EXIT -> exit();
                case START -> start(cmd.arguments());
                default -> UNKNOWN_COMMAND;
            };
        }
    }

    // example: add game0 pesho b h A2
    private String add(String[] arguments) {
        Game g = null;
        try {
            g = game.getGameBoard(arguments[0], arguments[1]);
        } catch (GameDoesntExistException e) {
            return "The game doesn't exist!";
        }
        Ship ship = Ship.of(arguments[2], arguments[3], arguments[4]);
        g.getMyBoard(arguments[1]).addShip(ship);
        return ship.type().toString();
    }

    // print game0 pesho
    private String print(String[] arguments) {
        Game g = null;
        try {
            g = game.getGameBoard(arguments[0], arguments[1]);
        } catch (GameDoesntExistException e) {
            return "The game doesn't exist!";
        }
        return String.format("%s\n\n%s",
                MatrixManipulation.getPrintableMatrix(
                        g.getEnemyBoard(arguments[1]), Player.ENEMY),
                MatrixManipulation.getPrintableMatrix(
                        g.getMyBoard(arguments[1]), Player.ME));
    }

    // start game0 pesho
    private String start(String[] arguments) {
        this.inGame = true;
        return print(arguments);
    }

    // exit
    private String exitGame() {
        this.inGame = false;
        return "Exit";
    }

    // hit game0 pesho A2
    private String hit(String[] arguments) {
        Game g = null;
        try {
            g = game.getGameBoard(arguments[0], arguments[1]);
        } catch (GameDoesntExistException e) {
            return "The game doesn't exist!";
        }
        if (g.isCurrentTurn(arguments[1])) {
            g.getEnemyBoard(arguments[1]).hit(arguments[2]);
            g.endTurn();
            return String.format("%s\n\n%s",
                    MatrixManipulation.getPrintableMatrix(
                    g.getEnemyBoard(arguments[1]), Player.ENEMY),
                    MatrixManipulation.getPrintableMatrix(
                            g.getMyBoard(arguments[1]), Player.ME));
        } else {
            return "NOT YOUR TURN!";
        }
    }

    private String list() {
        return game.list().toString();
    }
    private String save() {
        BattleshipsAPIUtils.saveToFile(game, FileCommand.SAVED);
        return "* SAVED *";
    }
    private String join(String[] arguments) {
        game.join(arguments[0], arguments[1]);
        return String.format("* JOINED: '%s' *", arguments[0]);
    }

    private String delete(String[] arguments) {
        game.removeGame(arguments[0]);
        return String.format("* GAME: '%s' IS SUCCESSFULLY DELETED *", arguments[0]);
    }

    private String create(String[] arguments) {
        game.createGame(arguments[0], arguments[1]);
        return "* NEW GAME SUCCESSFULLY CREATED *";
    }

    private String exit() {
        return DISCONNECTED;
    }
}
