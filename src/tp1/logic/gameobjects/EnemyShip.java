package tp1.logic.gameobjects;

import tp1.logic.*;

public abstract class EnemyShip extends Ship{
    protected int score = 0;
    public EnemyShip(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }
    @Override
    public boolean receiveAttack(UCMWeapon weapon) {
        this.dealDamage(weapon);
        return true;
    }

    public void onDelete() {
        super.onDelete();
        this.game.increaseScore(this.score);
    }
}
