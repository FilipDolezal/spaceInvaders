package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
    public Bomb(GameWorld game, Position pos, int life) {
        super(game, pos, life);
        this.dir = Move.DOWN;
    }

    @Override
    protected String getSymbol() {
        return Messages.BOMB_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 1;
    }

    @Override
    protected int getArmour() {
        return 0;
    }

    @Override
    public void onDelete() {

    }
}
