package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.util.MyStringUtils;
import tp1.view.Messages;

/**
 * Class of the UCMShip that contains all the attributes and methods.
 */
public class UCMShip extends Ship{
    /**
     * Constructor for the UCMShip.
     */
    public UCMShip(Game game, Position position) {
        super(game, position, 3);
    }

    @Override
    public boolean isOnPosition(Position pos) {
        return this.pos.equals(pos);
    }

    @Override
    protected String getSymbol() {
        return Messages.UCMSHIP_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 0;
    }

    @Override
    protected int getArmour() {
        return 0;
    }

    @Override
    public void onDelete() {

    }

    @Override
    public void automaticMove() {

    }

    @Override
    public boolean receiveAttack(EnemyWeapon weapon) {return false;}
}
