package game.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileCommandTest {

    @Test
    void fileExists() {
        assertTrue(FileCommand.fileExists("./src/command/Command.java"));
    }
}