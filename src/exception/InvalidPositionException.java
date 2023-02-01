package exception;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException() {
        super();
    }

    public InvalidPositionException(String message) {
        super(message);
    }
}
