package io;

import command.CommandExecutor;
import game.BattleshipsAPI;
import game.BattleshipsAPIUtils;
import game.command.FileCommand;

public class ServerStart {

    public static final int SERVER_PORT = 16969;

    public static void main(String[] args) {
        BattleshipsAPI game;
        if (!FileCommand.fileExists(FileCommand.SAVED)) {
            game = new BattleshipsAPI();
        } else {
            game = BattleshipsAPIUtils.loadFromFile(FileCommand.SAVED);
        }
        CommandExecutor commandExecutor = new CommandExecutor(game);
        Server server = new Server(SERVER_PORT, commandExecutor);
        server.start();
    }
}
