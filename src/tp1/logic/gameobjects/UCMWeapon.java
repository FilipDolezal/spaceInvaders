package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
    protected UCMShip ship;

    /**
     * Contructor of the UCMWeapon.
     * @param game Interface with all the functionality of the objects.
     * @param pos Position of the UCMWeapon.
     * @param life Life of the UCMWeapon.
     */
    public UCMWeapon(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    /**
     * Calculates whether the weapon is out of the board or if it has hit any other object.
     */
    @Override
    public void computerAction() {
        // next position is out of bounds? -> delete weapon
        if(!this.game.inBoundsY(this.pos.move(this.dir))) this.onDelete();

        boolean collision = this.game.performAttack(this);
        // weapon collided with anything? -> delete weapon
        if(collision) this.onDelete();
    }

    /**
     * Performs the attack over any other object in the board.
     * @param other GameItem
     * @return true if it has attacked something, false otherwise.
     */
    @Override
    public boolean performAttack(GameItem other) {
        if(!(other.isOnPosition(this.pos) || other.isOnPosition(this.pos.move(this.dir)))) return false;
        //If the position of the object is the same as the position of the weapon
        // or the next position is predicted, then it returns true, false otherwise.
        return other.receiveAttack(this);
    }
}
