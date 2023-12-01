package tp1.logic.gameobjects;

import tp1.logic.*;

public abstract class EnemyShip extends Ship{

    public EnemyShip(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        this.dealDamage(weapon);
        return true;
    }

    public void removeObject(GameObject object){

    }
}
