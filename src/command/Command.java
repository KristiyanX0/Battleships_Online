package command;

import java.util.Objects;

public record Command(String path, String command, String[] arguments) {
    String game() {
        String[] temp = path.split("_");
        if (temp.length == 1) {
            return "";
        }
        return temp[1];
    }
    String username() {
        return path.split("_")[0];
    }
}
