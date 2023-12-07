package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
    /**
     * Contractor of the Weapon
     * @param game Interface with all the functionality of the objects.
     * @param pos Position of the Weapon.
     * @param life Life of the Weapon.
     */
    public Weapon(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }
    public void computerAction() {}
    public void collision() {
        if(--this.life == 0) this.onDelete();
        //if the Weapon has collided with any object, it is deleted.
    }

}
