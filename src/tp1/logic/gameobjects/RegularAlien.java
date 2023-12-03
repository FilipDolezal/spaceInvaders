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

	public RegularAlien(GameWorld game, AlienManager alienManager, Position pos, int life) {
		super(game, alienManager, pos, life);
		super.score = 5;
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
	public void automaticMove() {
		super.automaticMove();
		System.out.println("am: " + this + ": " + this.pos + ": " + this.dir);
	}

	@Override
	public void computerAction() {
		super.computerAction();
		System.out.println("ca: " + this + ": " + this.pos + ": " + this.dir);
	}

	@Override
	public void onDelete() {
		super.onDelete();
	}
}