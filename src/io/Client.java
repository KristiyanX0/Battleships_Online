package io;

import command.CommandExecutor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import java.nio.ByteBuffer;

public class Client {
    private final int serverPort;
    private final String serverHost;
    private static ByteBuffer buffer;
    private final String profile;

    public Client(String serverHost, int serverPort, int bufferSize, String profile) {
        this.serverPort = serverPort;
        this.serverHost = serverHost;
        this.profile = profile;
        buffer = ByteBuffer.allocateDirect(bufferSize);
    }

    void start() {
        try (SocketChannel socketChannel = SocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(new InetSocketAddress(serverHost, serverPort));

            System.out.println("Connected to the server!");

            String game = null;

            while (true) {
                System.out.print("> ");
                String message = profile + " " + scanner.nextLine(); // read a line from the console

                writeClientInput(message, socketChannel);
                String reply = getServerReply(socketChannel);
                if (reply.split(" ")[0].equals("STARTGAME")) {
                    game = reply.split(" ")[1];
                    while (true) {
                        message = profile + "_" + game + " " + "print";
                        writeClientInput(message, socketChannel);
                        reply = getServerReply(socketChannel);
                        System.out.println(reply);

                        System.out.print("> ");
                        String scannerWrite = scanner.nextLine();
                        message = profile + "_" + game + " " + scannerWrite;
                        if (scannerWrite.equals("exit")) {
                            message = "GAMEEXIT";
                            game = null;
                            break;
                        }
                        writeClientInput(message, socketChannel);
                        reply = getServerReply(socketChannel);
                        System.out.println(reply);
                    }
                }
                if (CommandExecutor.DISCONNECTED.equals(message)) {
                    System.out.println(reply);
                    break;
                } else if (!message.equals("GAMEEXIT")) {
                    System.out.println(reply);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("There is a problem with the network communication", e);
        }
    }

    private String getServerReply(SocketChannel socketChannel) throws IOException {
        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();

        byte[] byteArray = new byte[buffer.remaining()];
        buffer.get(byteArray);
        return new String(byteArray, "UTF-8");
    }

    private void writeClientInput(String message, SocketChannel socketChannel) throws IOException {
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
    }
}
