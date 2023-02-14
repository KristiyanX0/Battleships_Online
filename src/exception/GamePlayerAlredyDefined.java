package exception;

public class GamePlayerAlredyDefined extends RuntimeException {
    public GamePlayerAlredyDefined() {
        super();
    }
    
    public GamePlayerAlredyDefined(String message) {
        super(message);
    }
}
