package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Move;
import tp1.logic.Position;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien extends Alien {

	public RegularAlien(AlienManager alienManager, Position position) {
		super(alienManager, position);
		this.dir = Move.RIGHT;
		this.symbol = 'R';
	}


}