package game.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogWriter {

    public static void log(String message, String filePath) {
        try {
            // Create the file and parent directories if they don't exist
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateTimeString = now.format(formatter);

            // Open the file for writing
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            // Write the message to the file, along with the date and time
            writer.write(dateTimeString + " - " + message);
            writer.newLine();

            // Close the file
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

}
