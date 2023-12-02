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

	@Override
	public void onDelete() {
		super.onDelete();
		this.ship.disableLaser();
	}

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}
}
