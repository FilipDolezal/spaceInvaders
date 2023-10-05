package tp1.logic.gameobjects;

import tp1.logic.Move;
import tp1.logic.Position;

public class UCMShip {

    public Position position;

    private UCMLaser laser;

    public UCMShip() {
        this.position = new Position(4, 7);
    }

    public void preformMovement(Move move) {
        this.position = position.move(move);
    }

    public void preformAttack() {
        if(laser == null) {
            laser = new UCMLaser(position, Move.UP);
        }
    }

    public UCMLaser getLaser() {
        return this.laser;
    }
}
