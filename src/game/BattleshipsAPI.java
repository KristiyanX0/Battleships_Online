package game;

import exception.*;
import game.helper.Randomise;

import java.io.Serializable;
import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Objects;
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
            throw new GameAlreadyExistException("Game already exist!");
        }
    }

    public void join(String gameName, String playerName, Boolean randomised) {
        if (!games.containsKey(gameName)) {
            throw new GameDoesntExistException("Game doesn't exist!");
        } else if (this.games.get(gameName).getPlayer1().equals(playerName)) {
            throw new GamePlayerAlredyDefined("Game full!");
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
            throw new GameDoesntExistException("Game doesn't exist!");
        }
        Game game = games.get(gameName);
        if (!attackerName.equals(game.getPlayer1()) &&
                !attackerName.equals(game.getPlayer2())) {
            throw new InvalidGameObject("Invalid game object");
        }
        return game;
    }

    public boolean removeGame(String name, String profileName) {
        if (!games.containsKey(name)) {
            throw new GameDoesntExistException("Game doesn't exist!");
        } else if ((!Objects.isNull(games.get(name).getPlayer2())) &&
                (games.get(name).getPlayer1().equals(profileName) ||
                games.get(name).getPlayer2().equals(profileName))) {
            games.remove(name);
            return true;
        } else {
            throw new DeniedAccessException("You don't have access!");
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

    public boolean startGame(String gameName, String playerName) {
        if (!games.containsKey(gameName)) {
            throw new GameDoesntExistException("Game doesn't exist!");
        } else if (Objects.isNull(games.get(gameName).getPlayer1()) ||
                Objects.isNull(games.get(gameName).getPlayer2())) {
            throw new NotEnoughPlayersToStartException("Not enough players to start!");
        } else if (games.get(gameName).isStarted()) {
            throw new GameAlreadyStartedException("Game Already Started!");
        } else if (playerName.equals(games.get(gameName).getPlayer1())) {
            games.get(gameName).setToStarted();
            return true;
        } else {
            throw new PlayerCannotStartGameException("Player Cannot start game exception!");
        }
    }

    public boolean loadGame(String gameName) {
        if (!games.containsKey(gameName)) {
            throw new GameDoesntExistException("Game doesn't exist!");
        } else if (!games.get(gameName).isStarted()) {
            throw new GameIsNotStartedException("Game is not started!");
        } else {
            return true;
        }
    }
}
