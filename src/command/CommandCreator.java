package command;

import java.util.Arrays;
import java.util.List;

public class CommandCreator {
    public static Command newCommand(String clientInput) {
        List<String> tokens = Arrays.stream(clientInput.split(" ")).toList();
        return new Command(tokens.get(0), tokens.subList(1, tokens.size()).toArray(String[]::new));
    }
}
