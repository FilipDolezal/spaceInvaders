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
	public RegularAlien(AlienManager alienManager, Position position) {
		this.alienManager 	= alienManager;
		this.position 		= position;
	}

	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	public void automaticMove() {
		performMovement(dir);
	}

	private void descent() {
		this.position = this.position.move(Move.DOWN);
	}

	private void performMovement(Move dir) {
		this.position = this.position.move(dir);
	}

	public void changeDirection() {
		this.position = position.move(Move.DOWN);
		this.dir = switch (this.dir) {
			case LEFT -> Move.RIGHT;
			case RIGHT -> Move.LEFT;
			default -> Move.NONE;
		};
	}

	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		return false;
	}

	public int getHealth() {
		return this.health;
	}
	

}