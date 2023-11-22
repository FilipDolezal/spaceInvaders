package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser extends UCMWeapon {
	public UCMLaser(Game game, Position pos, int life) {
		super(game, pos, life);
	}

	@Override
	protected String getSymbol() {
		return null;
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

	}

	@Override
	public void automaticMove() {

	}

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}
}
