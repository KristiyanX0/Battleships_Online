package game.command;

import files.FileType;
import operations.StringOperation;

import java.io.*;
import java.nio.file.*;

public class Command {
    public static final String HELP_PATH = "./src/files/body/help";
    public static final String LEGEND_PATH = "./src/files/body/legend";
    public static final String GAME_PATH = "./src/files/game";
    public static final String PROFILE_PATH = "./src/files/body/profile";

    private static String getPath(FileType fileType) {
        return switch (fileType) {
            case HELP -> HELP_PATH;
            case LEGEND -> LEGEND_PATH;
            case GAME -> GAME_PATH;
            case PROFILE -> PROFILE_PATH;
        };
    }

    public static String getFile(FileType fileType) {
        String file = null;
        try(BufferedReader reader = new BufferedReader(
                new FileReader(Path.of(getPath(fileType)).toString()))) {
            file = StringOperation.getFile(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void writeToFile(String content, String directory) {
        Path filePath = Paths.get(directory);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void createFile(String filePath) {
        Path file = Paths.get(filePath);

        try {
            // Create the parent directory if it doesn't exist
            Files.createDirectories(file.getParent());
            // Create an empty file
            Files.createFile(file);
            System.out.println("File created successfully: " + file);
        } catch (Exception e) {
            System.err.println("Failed to create file: " + file);
            e.printStackTrace();
        }
    }

    public static boolean fileExists(String directory) {
        Path filePath = Paths.get(directory);
        return Files.exists(filePath);
    }

    public static int countFilesInDirectory(String directory) {
        Path dirPath = Paths.get(directory);
        int fileCount = 0;
        try {
            fileCount = (int) Files.list(dirPath).count();
        } catch (IOException e) {
            System.out.println("Error counting files: " + e.getMessage());
        }
        return fileCount;
    }
}
