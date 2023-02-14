package command;

import game.BattleshipsAPI;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class CommandExecutorTest {

    @Test
    void TestExecuteCreate() {
        CommandExecutor commandExecutor = new CommandExecutor(new BattleshipsAPI());
        assertEquals("* NEW GAME SUCCESSFULLY CREATED *",
                commandExecutor.execute(CommandCreator.newCommand("create game0 kiko")));
    }

    @Test
    void TestExecuteDelete() {
        CommandExecutor commandExecutor = new CommandExecutor(new BattleshipsAPI());
        commandExecutor.execute(new Command("create", "game0 kiko".split(" ")));
        assertEquals("* GAME: 'game0' IS SUCCESSFULLY DELETED *",
                commandExecutor.execute(new Command("delete", "game0 kiko".split(" "))));
    }

    @Test
    void TestExecuteJoin() {
        CommandExecutor commandExecutor = new CommandExecutor(new BattleshipsAPI());
        commandExecutor.execute(new Command("create", "game0 kiko".split(" ")));
        assertEquals("* JOINED: 'game0' *",
                commandExecutor.execute(new Command("join", "game0 pesho".split(" "))));
    }

    @Test
    void TestExecuteList() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        CommandExecutor commandExecutor = new CommandExecutor(battleshipsAPI);
        assertEquals("1: Game - game0, Creator: kiko, Joined Count: 1\n",
                commandExecutor.execute(new Command("list", "".split(" "))));
    }

    @Test
    void TestExecuteAdd() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        battleshipsAPI.join("game0", "pesho");
        CommandExecutor commandExecutor = new CommandExecutor(battleshipsAPI);
        commandExecutor.execute(new Command("start", "game0 kiko".split(" ")));
        assertEquals("BATTLESHIP",
                commandExecutor.execute(new Command("add", "game0 pesho b h A2".split(" "))));
    }

    @Test
    void TestExecuteExit() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        battleshipsAPI.join("game0", "pesho");
        CommandExecutor commandExecutor = new CommandExecutor(battleshipsAPI);
        commandExecutor.execute(new Command("start", "game0 kiko".split(" ")));
        assertEquals("Exit",
                commandExecutor.execute(new Command("exit", "".split(" "))));
    }

    @Test
    void TestExecuteHit() {
        BattleshipsAPI battleshipsAPI = new BattleshipsAPI();
        battleshipsAPI.createGame("game0", "kiko");
        battleshipsAPI.join("game0", "pesho");
        CommandExecutor commandExecutor = new CommandExecutor(battleshipsAPI);
        commandExecutor.execute(new Command("start", "game0 kiko".split(" ")));
        commandExecutor.execute(new Command("add", "game0 pesho b h A2".split(" ")));
        assertEquals("   0 1 2 3 4 5 6 7 8 9\n" +
                        "A |-|-|X|-|-|-|-|-|-|-|\n" +
                        "B |-|-|-|-|-|-|-|-|-|-|\n" +
                        "C |-|-|-|-|-|-|-|-|-|-|\n" +
                        "D |-|-|-|-|-|-|-|-|-|-|\n" +
                        "E |-|-|-|-|-|-|-|-|-|-|\n" +
                        "F |-|-|-|-|-|-|-|-|-|-|\n" +
                        "G |-|-|-|-|-|-|-|-|-|-|\n" +
                        "H |-|-|-|-|-|-|-|-|-|-|\n" +
                        "I |-|-|-|-|-|-|-|-|-|-|\n" +
                        "J |-|-|-|-|-|-|-|-|-|-|\n" +
                        "\n" +
                        "\n" +
                        "   0 1 2 3 4 5 6 7 8 9\n" +
                        "A |-|-|-|-|-|-|-|-|-|-|\n" +
                        "B |-|-|-|-|-|-|-|-|-|-|\n" +
                        "C |-|-|-|-|-|-|-|-|-|-|\n" +
                        "D |-|-|-|-|-|-|-|-|-|-|\n" +
                        "E |-|-|-|-|-|-|-|-|-|-|\n" +
                        "F |-|-|-|-|-|-|-|-|-|-|\n" +
                        "G |-|-|-|-|-|-|-|-|-|-|\n" +
                        "H |-|-|-|-|-|-|-|-|-|-|\n" +
                        "I |-|-|-|-|-|-|-|-|-|-|\n" +
                        "J |-|-|-|-|-|-|-|-|-|-|\n",
                commandExecutor.execute(new Command("hit", "game0 kiko A2".split(" "))));
    }
}