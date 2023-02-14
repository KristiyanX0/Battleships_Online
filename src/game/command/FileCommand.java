package game.command;

import java.nio.file.*;

public class FileCommand {

    public static final String SAVED = "./src/files/game/Battleship";

    public static boolean fileExists(String directory) {
        Path filePath = Paths.get(directory);
        return Files.exists(filePath);
    }
}
