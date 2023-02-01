package game.Position;

import exception.InvalidPositionException;

public class Position {
    int x;
    int y;

    private Position(int x, int y) {
        if (!(0 <= x && x <= 9) || !(0 <= y && y <= 9)) {
            throw new InvalidPositionException("Invalid position");
        }
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public static Position of(char x, int y) {
        return of(x - 'A', y);
    }

    public static Position of(String str) {
        if (str.length() != 2) {
            throw new InvalidPositionException("Invalid position");
        }
        return of(str.charAt(0), str.charAt(1) - '0');
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
