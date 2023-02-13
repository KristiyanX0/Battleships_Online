package exception;

public class InvalidGameObject extends RuntimeException {
    public InvalidGameObject() {
        super();
    }
    public InvalidGameObject(String invalid_game_object) {
        super(invalid_game_object);
    }
}
