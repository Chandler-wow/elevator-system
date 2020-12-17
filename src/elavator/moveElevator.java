package elavator;

public enum moveElevator {
    UP(1), DOWN(-1), STAY(0);
    public final int move;

    private moveElevator(int move) {
        this.move = move;
    }
}
