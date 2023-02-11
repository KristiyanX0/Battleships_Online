package operations;

import java.io.BufferedReader;

public class StringOperation {
    public static String getFile(BufferedReader boardTemplate) {
        return boardTemplate.lines().toList().stream()
                .map(x -> x.concat(System.lineSeparator()))
                .reduce("",String::concat);
    }
}
