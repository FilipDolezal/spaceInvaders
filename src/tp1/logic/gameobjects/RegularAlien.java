package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien. <br>
 * Extends the abstract class of Alien.
 *
 */
public class RegularAlien extends AlienShip {

	public RegularAlien(Game game, Position pos, int life) {
		super(game, pos, life);
	}

	@Override
	protected String getSymbol() {
		return Messages.REGULAR_ALIEN_SYMBOL;
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