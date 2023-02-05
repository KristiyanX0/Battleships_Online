package exception;

public class InvalidMappingException extends RuntimeException {
    public InvalidMappingException() {
        super();
    }

    public InvalidMappingException(String message) {
        super(message);
    }
}
