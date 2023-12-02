package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
    private DestroyerAlien alien;

    public Bomb(GameWorld game, DestroyerAlien alien, Position pos, int life) {
        super(game, pos, life);
        this.dir = Move.DOWN;
        this.alien = alien;
    }

    public void computerAction() {
        super.computerAction();
        boolean inBounds = this.game.inBoundsY(this.pos);
        if(!inBounds) this.onDelete();
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

    public void onDelete() {
        super.onDelete();
        this.alien.deleteBomb();
    }
}
