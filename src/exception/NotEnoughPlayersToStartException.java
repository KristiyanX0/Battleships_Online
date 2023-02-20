package exception;

public class NotEnoughPlayersToStartException extends RuntimeException {
    public NotEnoughPlayersToStartException() {
        super();
    }
    public NotEnoughPlayersToStartException(String message) {
        super(message);
    }
}
