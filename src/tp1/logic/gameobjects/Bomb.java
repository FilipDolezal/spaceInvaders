package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
    private DestroyerAlien alien;

    /**
     * Constructor of the Bomb.
     * @param game Interface
     * @param alien Destroyer alien that the bomb belongs to
     * @param pos Position of the bomb
     * @param life Life of the bomb
     */
    public Bomb(GameWorld game, DestroyerAlien alien, Position pos, int life) {
        super(game, pos, life);
        this.dir = Move.DOWN;
        this.alien = alien;
    }

    /**
     * Moves the bomb while it is in bounds (in the board) otherwise it is deleted.
     */
    @Override
    public void automaticMove() {
        super.automaticMove();
        if(this.pos.outBoundsY()) this.onDelete();
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

    /**
     * Deletes the bomb once it has been hit by the laser, or it is out of bounds
     */
    public void onDelete() {
        super.onDelete();
        this.alien.deleteBomb();
    }
}
