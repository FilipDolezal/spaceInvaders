package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{

    public EnemyWeapon(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public boolean performAttack(GameItem other) {
        //Todo: implement attack
        return false;
    }

    @Override
    public boolean receiveAttack(UCMWeapon weapon) {return false;}
}
