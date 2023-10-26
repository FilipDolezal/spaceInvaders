package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class UCMShip {
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

}
