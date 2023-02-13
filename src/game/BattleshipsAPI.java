package game;

import exception.InvalidPasswordException;
import exception.ProfileAlreadyExist;
import exception.ProfileDoesntExist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleshipsAPI implements Serializable {
    private final Map<String, Profile> profiles = new ConcurrentHashMap<>();
    final Map<String, Game> games = new ConcurrentHashMap<>();
    public Profile login(String name, String password) {
        if (profiles.containsKey(name)) {
            Profile prof = profiles.get(name);
            if (prof.getPassword().equals(password)) {
                return prof;
            }
            throw new InvalidPasswordException();
        }
        throw new ProfileDoesntExist();
    }

    public void createProfile(String name, String password) {
        if (profiles.containsKey(name)) {
            throw new ProfileAlreadyExist();
        }
        profiles.put(name, new Profile(name, password, this));
    }
}
