package game;

import exception.*;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleshipsAPI implements Serializable {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public void createGame(String name, String playerName) {
        if (!games.containsKey(name)) {
            Game game = new Game(playerName);
            games.put(name, game);
        } else {
            throw new GameAlreadyExistException();
        }
    }

    public void join(String gameName, String playerName) {
        if (!games.containsKey(gameName)) {
            throw new GameDoesntExistException();
        } else {
            this.games.get(gameName).add(playerName);
        }
    }

    public Game getGameBoard(String gameName, String attackerName) {
        if (!games.containsKey(gameName)) {
            throw new GameDoesntExistException();
        }
        Game game = games.get(gameName);
        if (!attackerName.equals(game.getPlayer1()) &&
                !attackerName.equals(game.getPlayer2())) {
            throw new InvalidGameObject();
        }
        return game;
    }

    public void removeGame(String name) {
        games.remove(name);
    }

    public StringBuilder list() {
        int i = 1;
        StringBuilder format = new StringBuilder("");
        for (var x : games.entrySet()) {
            format.append(String.format("%s: Game - %s, Creator: %s, Joined Count: %s%s", i++, x.getKey(),
                    x.getValue().getPlayer1(), x.getValue().getJoinedCount(), System.lineSeparator()));
        }
        return format;
    }
}
