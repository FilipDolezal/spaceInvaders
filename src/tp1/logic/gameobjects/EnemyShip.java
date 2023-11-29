package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship{

    public EnemyShip(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        this.dealDamage(weapon);
        return true;
    }
}
