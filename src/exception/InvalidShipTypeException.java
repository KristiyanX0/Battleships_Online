package exception;

public class InvalidShipTypeException extends RuntimeException {
    public InvalidShipTypeException() {
        super();
    }
    public InvalidShipTypeException(String message) {
        super(message);
    }
}
