package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * Class of the SuperLaser which is the same as the UCMLaser but with
 * small changes fired by the {@link UCMShip}
 */
public class SuperLaser extends UCMWeapon {
    public static final int COST = 5;

    /**
     * Constructor of the SuperLaser
     * @param game Interface with all the functionality of the objects.
     * @param ship The SuperLaser is related to the UCMShip.
     */
    public SuperLaser(GameWorld game, UCMShip ship) {
        super(game, ship.getPos(), 1);
        this.ship = ship;
        this.dir = Move.UP;
    }

    @Override
    protected String getSymbol() {
        return Messages.SUPER_LASER_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 2;
    }

    @Override
    protected int getArmour() {
        return 0;
    }

    /**
     * Deletes the SuperLaser.
     */
    @Override
    public void onDelete() {
        super.onDelete();
        this.ship.disableSuperLaser();
    }
}
