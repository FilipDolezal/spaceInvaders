package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

/**
 * This class manages the UFO. <br>
 * Contains the attributes and the movement of the UFO.
 */

public class Ufo extends EnemyShip {
	public Ufo(Game game, Position pos, int life) {
		super(game, pos, life);
	}

	@Override
	public boolean isOnPosition(Position pos) {
		return false;
	}

	@Override
	protected String getSymbol() {
		return null;
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
}
