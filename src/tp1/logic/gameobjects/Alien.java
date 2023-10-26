package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Move;
import tp1.logic.Position;

    public abstract class Alien {
    protected char symbol;
    protected int speed, health = 1;

    protected Move dir;

    protected Position position;
    protected AlienManager alienManager;

    public Alien(AlienManager alienManager, Position position){
        this.alienManager 	= alienManager;
        this.position 		= position;
    }

    /**
     *  Implements the automatic movement of the regular alien
     */
    public void automaticMove() {
        this.performMovement(dir);
    }

    private void performMovement(Move dir) {
        this.position = this.position.move(dir);
    }

    public void changeDirection() {
        this.position = position.move(Move.DOWN);
        this.dir = switch (this.dir) {
            case LEFT -> Move.RIGHT;
            case RIGHT -> Move.LEFT;
            default -> Move.NONE;
        };
    }

    public boolean receiveAttack() {
        if(--this.health == 0) {
            this.alienManager.removeAlien(this);
            return true;
        }
        return false;
    }

    public String toString() {
        return this.symbol + "[" + this.health + "]";
    }

    public Position getPosition() {
        return position;
    }
}
