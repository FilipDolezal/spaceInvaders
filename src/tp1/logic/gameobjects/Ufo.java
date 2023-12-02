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
	public Ufo(GameWorld game, Position pos, int life) {
		super(game, pos, life);
		//this.pos = new Position (Game.DIM_X - 1, 0);	//Ufo starts on col DIMX-1 and row 0
		this.dir = Move.LEFT;		//His direction is always to the left
	}

	@Override
	protected String getSymbol() {
		return " " + Messages.UFO_SYMBOL + '[' + '0' + this.life + ']';
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
		super.onDelete();
		this.game.obtainShockwave();
	}

	@Override
	public void computerAction() {
		if(!this.game.inBoundsX(this.pos.move(this.dir)))
			super.onDelete();
	}
}
