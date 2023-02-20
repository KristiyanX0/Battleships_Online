package command;

import exception.GameDoesntExistException;
import game.BattleshipsAPI;
import game.BattleshipsAPIUtils;
import game.Game;
import game.Player;
import game.command.FileCommand;
import game.helper.MatrixManipulation;
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
    private static final String ADD = "add";
    /* =========================================================== */

    /* ===================== INGAME COMMAND ====================== */
    private static final String HIT = "hit";
    private static final String EXIT = "exit";
    /* =========================================================== */
    private static final String UNKNOWN_COMMAND = "Unknown command";
    public static final String DISCONNECTED = "Disconnected from the server";
    private final BattleshipsAPI game;

    public CommandExecutor(BattleshipsAPI game) {
        this.game = game;
    }
    public String execute(Command cmd) {
        if (!cmd.game().isEmpty()) {
            return switch (cmd.command()) {
                case ADD -> add(cmd);
                case HIT -> hit(cmd);
                case EXIT -> exitGame();
                case PRINT -> print(cmd);
                default -> UNKNOWN_COMMAND;
            };
        } else {
            return switch (cmd.command()) {
                case CREATE -> create(cmd);
                case DELETE -> delete(cmd);
                case SAVE -> save();
                case LIST -> list();
                case JOIN -> join(cmd);
                case EXIT -> exit();
                case START -> start(cmd);
                default -> UNKNOWN_COMMAND;
            };
        }
    }

    // add b h A2
    private String add(Command cmd) {
        String[] arguments = cmd.arguments();
        String profile = cmd.username();
        Game g = null;
        try {
            g = game.getGameBoard(cmd.game(), profile);
        } catch (GameDoesntExistException e) {
            return "The game doesn't exist!";
        }
        Ship ship = Ship.of(arguments[0], arguments[1], arguments[2]);
        g.getMyBoard(profile).addShip(ship);
        return ship.type().toString() + " " + arguments[2];
    }

    // print
    private String print(Command cmd) {
        Game g = null;
        try {
            g = game.getGameBoard(cmd.game(), cmd.username());
        } catch (GameDoesntExistException e) {
            return "The game doesn't exist!";
        }
        return String.format("%s\n\n%s",
                MatrixManipulation.getPrintableMatrix(
                        g.getEnemyBoard(cmd.username()), Player.ENEMY),
                MatrixManipulation.getPrintableMatrix(
                        g.getMyBoard(cmd.username()), Player.ME));
    }

    // start game0
    private String start(Command cmd) {
        return "STARTGAME" + " " + cmd.arguments()[0];
    }

    // exit
    private String exitGame() {
        return "Exit";
    }

    // hit A2
    private String hit(Command cmd) {
        String profile = cmd.username();
        String[] arguments = cmd.arguments();
        Game g = null;
        try {
            g = game.getGameBoard(cmd.game(), profile);
        } catch (GameDoesntExistException e) {
            return "The game doesn't exist!";
        }
        if (g.isCurrentTurn(profile)) {
            g.getEnemyBoard(profile).hit(arguments[0]);
            g.endTurn();
            return "HIT!";
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
    private String join(Command cmd) {
        try {
            if (cmd.arguments().length == 2) {
                game.join(cmd.arguments()[0], cmd.username(),
                        !cmd.arguments()[1].equals("add"));
            } else if (cmd.arguments().length == 1) {
                game.join(cmd.arguments()[0], cmd.username(), true);
            }
        } catch (GameDoesntExistException e) {
            return "GameDesntExist!";
        }
        return String.format("* JOINED: '%s' *", cmd.arguments()[0]);
    }

    private String delete(Command cmd) {
        game.removeGame(cmd.arguments()[0], cmd.username());
        return String.format("* GAME: '%s' IS SUCCESSFULLY DELETED *", cmd.arguments()[0]);
    }

    private String create(Command cmd) {
        if (cmd.arguments().length == 2) {
            game.createGame(cmd.arguments()[0], cmd.username(),
                    !cmd.arguments()[1].equals("add"));
        } else if (cmd.arguments().length == 1) {
            game.createGame(cmd.arguments()[0], cmd.username(), true);
        }
        return "* NEW GAME SUCCESSFULLY CREATED *";
    }

    private String exit() {
        return DISCONNECTED;
    }
}
