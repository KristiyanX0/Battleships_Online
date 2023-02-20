package exception;

public class GameIsNotStartedException extends RuntimeException {
    public GameIsNotStartedException() {
        super();
    }

    public GameIsNotStartedException(String message) {
        super(message);
    }

}
