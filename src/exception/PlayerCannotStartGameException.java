package exception;

public class PlayerCannotStartGameException extends RuntimeException {
    public PlayerCannotStartGameException() {
        super();
    }

    public PlayerCannotStartGameException(String message) {
        super(message);
    }
}
