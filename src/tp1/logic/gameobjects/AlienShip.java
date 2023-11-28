package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
    protected AlienManager alienManager;

    public AlienShip(Game game, AlienManager alienManager, Position pos, int life) {
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
