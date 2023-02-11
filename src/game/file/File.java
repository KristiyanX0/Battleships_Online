package game.file;

import files.FileType;
import operations.StringOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class File {
    private static final String HELP_PATH = "./src/files/body/help";
    private static final String LEGEND_PATH = "./src/files/body/legend";

    private static String getPath(FileType fileType) {
        return switch (fileType) {
            case HELP -> HELP_PATH;
            case LEGEND -> LEGEND_PATH;
        };
    }

    public static String getFile(FileType fileType) {
        String legend = null;
        try(BufferedReader boardTemplate = new BufferedReader(
                new FileReader(Path.of(getPath(fileType)).toString()))) {
            legend = StringOperation.getFile(boardTemplate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return legend;
    }

}
