package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser {
	private Move dir;
	private Game game;
	private Position position;

	public UCMLaser(Position position, Move dir, Game game) {
		this.game = game;
		this.position = position;
		this.dir = dir;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
	public void onDelete() {
		game.disableLaser();
	}

	/**
	 *  Implements the automatic movement of the laser	
	 */
	public void automaticMove () {
		performMovement(dir);
		if(isOut()) die();
	}

	private void die() {
		onDelete();
	}

	private boolean isOut() {
		return this.game.isOutOfBoundY(this.position);
	}

	private void performMovement(Move dir) {
		this.position = position.move(dir);
	}

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(Alien other) {
		boolean isHit = this.position
				.move(dir)
				.equals(other.getPosition());

		return isHit ? this.weaponAttack(other): false;
	}

	/**
	 * 
	 * @param other regular alien under attack by the laser
	 * @return always returns <code>true</code>
	 */
	private boolean weaponAttack(Alien other) {
		this.game.disableLaser();
		return other.receiveAttack();
	}

	//TODO fill your code


	// RECEIVE ATTACK METHODS
	
	/**
	 * Method to implement the effect of bomb attack on a laser
	 * @param weapon the received bomb
	 * @return always returns <code>true</code>
	 */
	/*
	public boolean receiveAttack(Bomb weapon) {
		receiveDamage(weapon.getDamage());
		return true;
	}
	*/

	public Position getPosition() {
		return position;
	}
}
