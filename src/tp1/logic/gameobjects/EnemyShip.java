package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{
    public EnemyShip(Game game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        if(!this.pos.equals(weapon.pos)) return false;

        this.dealDamage(weapon);
        return true;
    }

}
