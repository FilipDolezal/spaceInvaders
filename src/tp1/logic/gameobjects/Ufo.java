package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * This class manages the UFO. <br>
 * Contains the attributes and the movement of the UFO.
 */
public class Ufo extends EnemyShip {
	/**
	 * Contructor of the UFO.
	 * @param game Interface with all the functionality of the objects.
	 * @param pos Position of the UFO.
	 * @param life Life of the UFO.
	 */
	public Ufo(GameWorld game, Position pos, int life) {
		super(game, pos, life);
		this.dir = Move.LEFT;
		this.score = 25;
	}

	@Override
	protected String getSymbol() {
		return Messages.UFO_SYMBOL;
	}

	@Override
	protected int getDamage() {
		return 0;
	}

	@Override
	protected int getArmour() {
		return this.armor;
	}

	@Override
	public void onDelete() {
		super.onDelete();	//UFO is deleted when called.
		this.game.obtainShockwave(); //Gives the shockwave to the player if he has hit the UFO.
		this.game.increaseScore(this.score); //Increases the score.
	}

	@Override
	public void automaticMove() {
		super.automaticMove();	//Moves the ship automatically.
		if(!this.game.inBoundsX(this.pos)){	//If the ship is out of the bounds of the board, just deletes the UFO
			//without giving the Shockwave to the Player.
			super.onDelete();
			this.life = 0;
		}

	}
}
