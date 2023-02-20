package exception;

public class ShipIntersectionException extends RuntimeException {
    public ShipIntersectionException() {
        super();
    }

    public ShipIntersectionException(String message) {
        super(message);
    }
}
