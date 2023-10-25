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
	
	//TODO fill your code
	private Move dir;
	private Game game;

	public Position position;

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
		if(isOut())
			die();
	}

	
	// PERFORM ATTACK METHODS
	
	
	
	
	private void die() {
		onDelete();
		//TODO fill your code
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
	public boolean performAttack(RegularAlien other) {
		//TODO fill your code
		return false;
	}

	/**
	 * Method that implements the attack by the laser to a destroyer alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the destroyer alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */

	/*
	public boolean performAttack(DestroyerAlien other) {
		//TODO fill your code
		return false;
	}
	*/
	
	//TODO fill your code


	//ACTUAL ATTACK METHODS
	

	/**
	 * 
	 * @param other regular alien under attack by the laser
	 * @return always returns <code>true</code>
	 */
	public boolean weaponAttack(Alien other) {
		boolean isHit = this.position
				.move(dir)
				.equals(other.position);

		if(isHit) {
			this.game.UCMship.disableLaser();
			return other.receiveAttack(this);
		}
		return false;
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

}
