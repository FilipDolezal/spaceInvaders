package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{
    /**
     * Constructor of EnemyWeapon
     * @param game Interface
     * @param pos   Position
     * @param life  of the Enemy Weapon
     */
    public EnemyWeapon(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    public void computerAction() {
        this.game.performAttack(this);  //Performs an attack with the EnemyWeapon
    }

    @Override
    public boolean performAttack(GameItem other) {
        if(!other.isOnPosition(this.pos)) return false; //If the object is not in the same position
        // as the EnemyWeapon, returns false, true otherwise.

        return other.receiveAttack(this);
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        this.collision();   //Subtracts one life to the bomb if hits the UCMWeapon.
        return true;
    }
}
