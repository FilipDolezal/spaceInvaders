package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{

    public EnemyWeapon(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    public void computerAction() {
        this.game.performAttack(this);
    }

    @Override
    public boolean performAttack(GameItem other) {
        if(!other.isOnPosition(this.pos)) return false;
        return other.receiveAttack(this);
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        this.collision();
        return true;
    }
}
