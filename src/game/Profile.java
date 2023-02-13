package game;

import exception.GameAlreadyExistException;
import exception.GameDoesntExistException;

import java.io.Serializable;
import java.util.HashMap;

public class Profile implements Serializable {
    private final String playerName;
    private final String password;
    private final HashMap<String, Game> games = new HashMap<>();
    private final BattleshipsAPI battleshipsAPI;

    Profile(String playerName, String password, BattleshipsAPI battleshipsAPI) {
        this.playerName = playerName;
        this.password = password;
        this.battleshipsAPI = battleshipsAPI;
    }

    public void createGame(String name) {
        if (!games.containsKey(name)) {
            Game game = new Game(playerName);
            games.put(name, game);
            battleshipsAPI.games.put(name, game);
        } else {
            throw new GameAlreadyExistException();
        }
    }

    public Game join(String name) {
        if (!battleshipsAPI.games.containsKey(name)) {
            throw new GameDoesntExistException();
        } else if (games.containsKey(name)) {
            return this.games.get(name);
        } else {
            this.games.get(name).add(playerName);
            return this.games.get(name);
        }
    }

    public BattleshipsAPI logout() {
        return battleshipsAPI;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPassword() {
        return password;
    }
}
