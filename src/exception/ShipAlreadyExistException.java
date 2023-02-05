package exception;

public class ShipAlreadyExistException extends RuntimeException {
    public ShipAlreadyExistException() {
        super();
    }

    public ShipAlreadyExistException(String message) {
        super(message);
    }
}
