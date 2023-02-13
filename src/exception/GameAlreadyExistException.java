package exception;

public class GameAlreadyExistException extends RuntimeException {
    public GameAlreadyExistException() {
        super();
    }

    public GameAlreadyExistException(String message) {
        super(message);
    }
}
