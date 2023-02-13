package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BattleshipsAPIUtils {
    public static void saveToFile(BattleshipsAPI api, String filePath) {
        Path path = Paths.get(filePath);
        saveToFile(api, path);
    }

    public static BattleshipsAPI loadFromFile(String filePath) {
        Path path = Paths.get(filePath);
        return loadFromFile(path);
    }

    public static void saveToFile(BattleshipsAPI api, Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(filePath, StandardOpenOption.CREATE))) {
            outputStream.writeObject(api);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BattleshipsAPI loadFromFile(Path filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(filePath))) {
            return (BattleshipsAPI) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

