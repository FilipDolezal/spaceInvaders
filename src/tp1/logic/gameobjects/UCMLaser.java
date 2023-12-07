package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser extends UCMWeapon {
	/**
	 * Constructor of the UCMLaser.
	 * @param game Interface with all the functionality of the objects.
	 * @param ship The UCMLaser is related to the UCMShip.
	 */
	public UCMLaser(GameWorld game, UCMShip ship) {
		super(game, ship.getPos(), 1);
		this.ship = ship;
		this.dir = Move.UP;
	}

	@Override
	protected String getSymbol() {
		return Messages.LASER_SYMBOL;
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
	 * Deletes the UCMLaser when called.
	 */

	@Override
	public void onDelete() {
		super.onDelete();
		this.ship.disableLaser();
	}
}
