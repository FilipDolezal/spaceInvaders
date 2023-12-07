package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.util.MyStringUtils;

public abstract class AlienShip extends EnemyShip {
    protected AlienManager alienManager;
    /**
        Constructor of the AlienShip to initialize its attributes
     */
    public AlienShip(GameWorld game, Position pos, AlienManager alienManager) {
        super(game, pos, 0);
        this.alienManager = alienManager;
        this.dir = alienManager.getAlienShipMove();
    }

    public AlienShip() {

    }
    /**
    ComputerAction checks if the alienShips are on border.
     */
    @Override
    public void computerAction() {
        alienManager.isOnBorder(this.pos);
    }
    /**
    Moves all the alien ships
     */
    @Override
    public void automaticMove() {
        // perform movement with calculated move
        this.performMovement(alienManager.getAlienShipMove());
        // if alien is in last row -> set aliensWin to true
        if(pos.inRow(Game.DIM_Y - 1)) this.alienManager.setAliensWin();
    }

    @Override
    public void onDelete() {
        super.onDelete();       //Deletes the alien ship
        this.game.decreaseAlienCount();
        this.game.increaseScore(this.score);
    }

    protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am);
}
