package exception;

public class ProfileAlreadyExist extends RuntimeException {
    public ProfileAlreadyExist() {
        super();
    }

    public ProfileAlreadyExist(String message) {
        super(message);
    }
}
