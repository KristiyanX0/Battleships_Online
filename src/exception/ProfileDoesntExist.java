package exception;

public class ProfileDoesntExist extends RuntimeException {
    public ProfileDoesntExist() {
        super();
    }

    public ProfileDoesntExist(String message) {
        super(message);
    }
}
