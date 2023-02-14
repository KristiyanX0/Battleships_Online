package io;

public class Client1 {
    private static final int SERVER_PORT = 16969;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFFER_SIZE = 6144;

    public static void main(String[] args) {
        Client client = new Client(SERVER_HOST, SERVER_PORT, BUFFER_SIZE);
        client.start();
    }
}
