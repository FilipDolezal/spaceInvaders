package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien {

	//TODO fill your code
	private int cyclesToMove;
	private int speed, health;
	private Move dir;

	public Position position;
	
	private AlienManager alienManager;

	//TODO fill your code
	public RegularAlien(Position position) {
		this.position = position;
	}

	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	public void automaticMove() {
		//TODO fill your code
	}

	private void descent() {
		//TODO fill your code
		
	}

	private void performMovement(Move dir) {
		//TODO fill your code
		
	}

	private boolean isInBorder() {
		//TODO fill your code
		return false;
	}

	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		return false;
	}

	public int getHealth() {
		return this.health;
	}
	

}