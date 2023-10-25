package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class UCMShip {

    public Position position;

    public UCMLaser laser;
    private int health = 3;

    public UCMShip() {
        this.position = new Position(4, 7);
    }

    public void preformMovement(Move move) {
        this.position = position.move(move);
    }

    public void preformAttack(Game game) {
        if(laser == null) {
            laser = new UCMLaser(position, Move.UP, game);
        }
    }

    public UCMLaser getLaser() {
        return this.laser;
    }

    public void disableLaser() { this.laser = null; }

    public boolean isAlive() {
        return health > 0;
    }

}
