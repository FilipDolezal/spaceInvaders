package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class UCMShip {
    public static final String SYMBOL = "^__^";
    private Position position;
    private int health = 3;
    private int points;

    public UCMShip() {
        this.points = 0;
        this.position = new Position(4, 7);
    }

    public int getPoints() {
        return points;
    }

    public void preformMovement(Move move) {
        this.position = position.move(move);
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void receiveAttack() { this.health--; }

}
