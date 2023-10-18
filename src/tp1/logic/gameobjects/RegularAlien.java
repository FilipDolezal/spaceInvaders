package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
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
	private int speed, health = 1;

	private Move dir = Move.RIGHT;;

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
		if(--this.health == 0) {
			this.alienManager.regularAliens.remove(this);
			return true;
		}
		return false;
	}

	public int getHealth() {
		return this.health;
	}

}