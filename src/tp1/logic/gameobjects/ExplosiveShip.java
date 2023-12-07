package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveShip extends AlienShip {
    /**
     * Constructor of the ExplosiveShip
     * @param game  Interface
     * @param pos   Position
     * @param am    Alien Manager
     */
    public ExplosiveShip(GameWorld game, Position pos, AlienManager am) {
        super(game, pos, am);
        super.score = 12;
        super.life = 2;
    }

    public ExplosiveShip() {}

    @Override
    public void onDelete() {
        super.onDelete();

        // on delete create an explosion
        Explosion explosion = new Explosion(this.game, this.pos);
        this.game.performAttack(explosion);
    }

    /**
     * Creates a copy of the Explosive Ship.
     * @param game
     * @param pos
     * @param am
     * @return
     */
    @Override
    protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
        return new ExplosiveShip(game, pos, am);
    }

    @Override
    protected String getSymbol() {
        return Messages.EXPLOSIVE_ALIEN_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 0;
    }

    @Override
    protected int getArmour() {
        return 0;
    }
}
