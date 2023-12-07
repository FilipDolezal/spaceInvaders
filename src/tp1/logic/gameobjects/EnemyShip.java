package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.util.MyStringUtils;

public abstract class EnemyShip extends Ship{
    protected int score = 0;

    /**
     * Constructor of EnemyShip
     * @param game
     * @param pos
     * @param life
     */
    public EnemyShip(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    public EnemyShip() {

    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        this.dealDamage(weapon);
        return true;
    }

    @Override
    public String toString() {
        return MyStringUtils.center(        //returns the Symbol+life of the alienship
                String.format("%s[%02d]", this.getSymbol(), this.getLife()),
                7
        );
    }
    public void onDelete() {
        super.onDelete();
    }   //Deletes the object
}
