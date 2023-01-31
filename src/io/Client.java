package io;

import command.CommandExecutor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    private final int serverPort;
    private final String serverHost;
    private static ByteBuffer buffer;

    public Client(String serverHost, int serverPort, int bufferSize) {
        this.serverPort = serverPort;
        this.serverHost = serverHost;
        buffer = ByteBuffer.allocateDirect(bufferSize);
    }

    void start() {
        try (SocketChannel socketChannel = SocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(new InetSocketAddress(serverHost, serverPort));

            System.out.println("Connected to the server!");

            while (true) {
                System.out.print("=> ");
                String message = scanner.nextLine(); // read a line from the console

                writeClientInput(message, socketChannel);
                String reply = getServerReply(socketChannel);

                if (CommandExecutor.DISCONNECTED.equals(message)) {
                    System.out.println(reply);
                    break;
                }

                System.out.println(reply);
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
