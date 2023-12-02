package tp1.logic.gameobjects;

import tp1.logic.*;

public abstract class AlienShip extends EnemyShip {
    protected AlienManager alienManager;
    private boolean AliensInFinalRow;

    public AlienShip(GameWorld game, AlienManager alienManager, Position pos, int life) {
        super(game, pos, life);
        this.alienManager = alienManager;
        this.dir = alienManager.getAlienShipMove();
    }

    public void computerAction() {
        this.dir = alienManager.getAlienShipMove();
    }

    @Override
    public String toString() {
        return this.getSymbol() + "[" + this.getLife() + "]";
    }
}
