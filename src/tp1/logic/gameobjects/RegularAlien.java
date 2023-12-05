package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien. <br>
 * Extends the abstract class of Alien.
 *
 */
public class RegularAlien extends AlienShip {

	public RegularAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, alienManager);
		super.score = 5;
		super.life = 2;
	}

	public RegularAlien() {

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
		super.onDelete();
	}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new RegularAlien(game, pos, am);
	}
}