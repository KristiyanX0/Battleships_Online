package game;

import exception.*;
import game.helper.Randomise;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleshipsAPI implements Serializable {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public void createGame(String name, String playerName) {
        createGame(name, playerName, false);
    }

    public void createGame(String name, String playerName, Boolean randomised) {
        if (!games.containsKey(name)) {
            Game game;
            if (randomised) {
                game = Game.of(playerName, Randomise.randomisedBoard());
            } else {
                game = Game.of(playerName);
            }
            games.put(name, game);
        } else {
            throw new GameAlreadyExistException();
        }
    }

    public void join(String gameName, String playerName, Boolean randomised) {
        if (!games.containsKey(gameName)) {
            throw new GameDoesntExistException();
        } else if (this.games.get(gameName).getPlayer1().equals(playerName)) {
            throw new GamePlayerAlredyDefined("Game already defined!");
        } else {
            if (randomised) {
                this.games.get(gameName).add(playerName, Randomise.randomisedBoard());
            } else {
                this.games.get(gameName).add(playerName);
            }
        }
    }

    public void join(String gameName, String playerName) {
        join(gameName, playerName, true);
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

    public void removeGame(String name, String profileName) {
        if (games.containsKey(name) &&
                (games.get(name).getPlayer1().equals(profileName) ||
                        games.get(name).getPlayer2().equals(profileName))) {
            games.remove(name);
        }
    }

    public StringBuilder list() {
        int i = 1;
        StringBuilder format = new StringBuilder();
        if (games.isEmpty()) {
            format.append("EMPTY!");
        } else {
            for (var x : games.entrySet()) {
                format.append(String.format("%s: Game - %s, Creator: %s, Joined Count: %s%s", i++, x.getKey(),
                        x.getValue().getPlayer1(), x.getValue().getJoinedCount(), System.lineSeparator()));
            }
        }
        return format;
    }
}
