package io;

import command.CommandExecutor;
import game.BattleshipsAPI;
import game.BattleshipsAPIUtils;

public class ServerStart {

    public static final int SERVER_PORT = 6969;

    public static void main(String[] args) {
        BattleshipsAPI game = BattleshipsAPIUtils.loadFromFile("./src/files/game/Game0.txt");
        CommandExecutor commandExecutor = new CommandExecutor(game);
        Server server = new Server(SERVER_PORT, commandExecutor);
        server.start();
    }
}
