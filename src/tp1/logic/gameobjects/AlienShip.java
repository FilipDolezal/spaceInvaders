package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.util.MyStringUtils;

public abstract class AlienShip extends EnemyShip {
    protected AlienManager alienManager;
    public AlienShip(GameWorld game, Position pos, AlienManager alienManager) {
        super(game, pos, 0);
        this.alienManager = alienManager;
        this.dir = alienManager.getAlienShipMove();
    }

    public AlienShip() {

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
        return MyStringUtils.center(
                String.format("%s[%02d]", this.getSymbol(), this.getLife()),
                7
        );
    }

    protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am);
}
