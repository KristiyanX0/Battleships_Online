package command;

import game.BattleshipsAPI;
import game.Profile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor {

    /* ===================== FIRST COMMAND ======================= */
    private static final String CREATE = "create";
    private static final String LOGIN = "login";
    private static final String JOIN = "join";
    private static final String DELETE = "delete";
    /* =========================================================== */

    /* ========== SECOND COMMAND (DEPENDS ON FIRST ONE) ========== */
    private static final String GAME = "game";
    private static final String PROFILE = "profile";
    /* =========================================================== */

    /* ===================== INGAME COMMAND ====================== */
    private static final String EXIT = "exit";
    /* =========================================================== */

    private static final String UNKNOWN_COMMAND = "Unknown command";
    public static final String DISCONNECTED = "Disconnected from the server";

    private final BattleshipsAPI game;
    private final Map<String, Profile> clients = new ConcurrentHashMap<>();
    public void addClient(String clientId, Profile profile) {
        clients.put(clientId, profile);
    }
    public void removeClient(String clientId) {
        clients.remove(clientId);
    }

    public CommandExecutor(BattleshipsAPI game) {
        this.game = game;
    }
    public String execute(Profile clientProfile, Command cmd) {
        return switch (cmd.command()) {
            case CREATE -> create(cmd.arguments());
            case LOGIN -> login(cmd.arguments());
            case JOIN -> join(cmd.arguments());
            case DELETE -> delete(cmd.arguments());
            //case EXIT ->
            default -> UNKNOWN_COMMAND;
        };
    }
    private String join(String[] arguments) {

        return String.format("* JOINED: '%s' *", arguments[0]);
    }

    private String login(String[] arguments) {
        //profile = game.login(arguments[0], arguments[1]);
        return String.format("* LOGED: '%s' *", arguments[0]);
    }

    private String delete(String[] arguments) {
        return switch (arguments[0]) {
            case GAME -> deleteGame(arguments[1]);
            case PROFILE -> deleteProfile(arguments[1]);
            default -> UNKNOWN_COMMAND;
        };
    }

    private String deleteProfile(String name) {

        return String.format("* PROFILE: '%s' IS SUCCESSFULLY DELETED *", name);
    }

    private String deleteGame(String name) {

        return String.format("* GAME: '%s' IS SUCCESSFULLY DELETED *", name);
    }

    private String create(String[] arguments) {
        return switch (arguments[0]) {
            case GAME -> createGame(arguments[1]);
            case PROFILE -> createProfile(arguments[1], arguments[2]);
            default -> UNKNOWN_COMMAND;
        };
    }

    private String createProfile(String name, String password) {
        game.createProfile(name, password);
        return "* NEW PROFILE SUCCESSFULLY CREATED *";
    }

    private String createGame(String name) {

        return "* NEW GAME SUCCESSFULLY CREATED *";
    }
}
