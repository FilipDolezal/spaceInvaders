package tp1.logic.gameobjects;

import tp1.logic.*;

public abstract class AlienShip extends EnemyShip {
    protected AlienManager alienManager;
    public AlienShip(GameWorld game, AlienManager alienManager, Position pos, int life) {
        super(game, pos, life);
        this.alienManager = alienManager;
        this.dir = alienManager.getAlienShipMove();
    }

    @Override
    public void computerAction() {
        alienManager.isOnBorder(this.pos);
    }

    @Override
    public void automaticMove() {
        // perform movement with calculated move
        this.performMovement(alienManager.getAlienShipMove());
        // if alien is in last row -> set aliensWin to true
        if(pos.inRow(Game.DIM_Y - 1)) this.alienManager.setAliensWin();
    }

    @Override
    public void onDelete() {
        super.onDelete();
        this.game.decreaseAlienCount();
        this.game.increaseScore(this.score);
    }

    @Override
    public String toString() {
        return this.getSymbol() + "[" + this.getLife() + "]";
    }
}
