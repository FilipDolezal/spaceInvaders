package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMShip {
    public static final String SYMBOL = Messages.UCMSHIP_SYMBOL;
    private Position position;
    private int health = 3;

    public UCMShip() {
        this.position = new Position(4, 7);
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

    public int getHealth() {
        return this.health;
    }
}
