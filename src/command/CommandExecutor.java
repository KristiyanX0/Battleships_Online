package command;

import exception.*;
import game.BattleshipsAPI;
import game.BattleshipsAPIUtils;
import game.Game;
import game.Player;
import game.helper.FileCommand;
import game.helper.ErrorLogWriter;
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
    private static final String LOAD = "load";
    private static final String AUTOSAVE = "autosave";

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
    private boolean autosave = false;
    private final BattleshipsAPI game;

    public CommandExecutor(BattleshipsAPI game) {
        this.game = game;
    }
    public String execute(Command cmd) {
        if (autosave) {
            save();
        }
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
                case AUTOSAVE-> autosave(cmd.arguments());
                case CREATE -> create(cmd);
                case DELETE -> delete(cmd);
                case SAVE -> save();
                case LIST -> list();
                case JOIN -> join(cmd);
                case EXIT -> exit();
                case START -> start(cmd);
                case LOAD -> load(cmd);
                default -> UNKNOWN_COMMAND;
            };
        }
    }

    private String autosave(String[] arguments) {
        if (arguments[0].equalsIgnoreCase("on")) {
            autosave = true;
        } else if (arguments[0].equalsIgnoreCase("off")) {
            autosave = false;
        }
        return String.format("* AUTOSAVE: %S *", (autosave) ? "ON" : "OFF");
    }

    // add b h A2
    private String add(Command cmd) {
        String[] arguments = cmd.arguments();
        String profile = cmd.username();
        Game g = null;
        try {
            g = game.getGameBoard(cmd.game(), profile);
        } catch (GameDoesntExistException |
                 InvalidGameObject e) {
            ErrorLogWriter.log(e.getMessage(), FileCommand.LOG_FILE);
            return e.getMessage();
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
            if (g.isFinished()) {
                if (g.winnerIsPlayer1()) {
                    return "PLAYER ONE WON!";
                } else {
                    return "PLAYER TWO WON!";
                }
            }
        } catch (GameDoesntExistException |
                 InvalidGameObject |
                GameNotFinishedException e) {
            ErrorLogWriter.log(e.getMessage(), FileCommand.LOG_FILE);
            return e.getMessage();
        }
        return String.format("%s\n\n%s",
                MatrixManipulation.getPrintableMatrix(
                        g.getEnemyBoard(cmd.username()), Player.ENEMY),
                MatrixManipulation.getPrintableMatrix(
                        g.getMyBoard(cmd.username()), Player.ME));
    }

    // start game0
    private String start(Command cmd) {
        try {
            if (game.startGame(cmd.arguments()[0], cmd.username())) {
                return "STARTGAME" + " " + cmd.arguments()[0];
            }
        } catch (GameDoesntExistException |
                 NotEnoughPlayersToStartException |
                 PlayerCannotStartGameException |
                 GameAlreadyStartedException e) {
            ErrorLogWriter.log(e.toString(), FileCommand.LOG_FILE);
            return e.getMessage();
        }
        return "INVALID CASE!";
    }

    private String load(Command cmd) {
        try {
            if (game.loadGame(cmd.arguments()[0])) {
                return "STARTGAME" + " " + cmd.arguments()[0];
            }
        } catch (GameDoesntExistException |
                 PlayerCannotStartGameException |
                GameIsNotStartedException e) {
            ErrorLogWriter.log(e.toString(), FileCommand.LOG_FILE);
            return e.getMessage();
        }
        return "INVALID CASE!";
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
            if (g.isCurrentTurn(profile)) {
                g.getEnemyBoard(profile).hit(arguments[0]);
                g.endTurn();
                return "HIT!";
            } else {
                return "NOT YOUR TURN!";
            }
        } catch (GameDoesntExistException |
                 InvalidGameObject |
                InvalidPositionException e) {
            ErrorLogWriter.log(e.getMessage(), FileCommand.LOG_FILE);
            return e.getMessage();
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
        } catch (GameDoesntExistException |
                 GamePlayerAlredyDefined e) {
            ErrorLogWriter.log(e.getMessage(), FileCommand.LOG_FILE);
            return e.getMessage();
        }
        return String.format("* JOINED: '%s' *", cmd.arguments()[0]);
    }

    private String delete(Command cmd) {
        try {
            game.removeGame(cmd.arguments()[0], cmd.username());
        } catch (GameDoesntExistException |
                 DeniedAccessException e) {
            ErrorLogWriter.log(e.getMessage(), FileCommand.LOG_FILE);
            return e.getMessage();
        }
        return String.format("* GAME: '%s' IS SUCCESSFULLY DELETED *", cmd.arguments()[0]);
    }

    private String create(Command cmd) {
        try {
            if (cmd.arguments().length == 2) {
                game.createGame(cmd.arguments()[0], cmd.username(),
                        !cmd.arguments()[1].equals("add"));
            } else if (cmd.arguments().length == 1) {
                game.createGame(cmd.arguments()[0], cmd.username(), true);
            }
        } catch (GameAlreadyExistException e) {
            ErrorLogWriter.log(e.getMessage(), FileCommand.LOG_FILE);
            return e.getMessage();
        }
        return "* NEW GAME SUCCESSFULLY CREATED *";
    }

    private String exit() {
        return DISCONNECTED;
    }
}
