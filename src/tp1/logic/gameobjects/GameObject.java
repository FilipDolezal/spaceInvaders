package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

/**
 * Abstract Class that every other object in game inherits.
 */
public abstract class GameObject implements GameItem {

	public Position getPos() {
		return pos;
	}

	protected Position pos;
	protected int life;
	protected GameWorld game;
	protected Move dir;

	/**
	 * Constructor of the Game Object.
	 * @param game Interface containing all the methods related to the functionality of the objects.
	 * @param pos Position.
	 * @param life Life of the object.
	 */
	public GameObject(GameWorld game, Position pos, int life) {
		this.pos = pos;
		this.game = game;
		this.life = life;
	}

	public GameObject(){

	}

	/**
	 * @return true if the object is alive, false otherwise.
	 */
	@Override
	public boolean isAlive() {
		return this.life > 0;
	}
	public int getLife() {
		return this.life;
	}

	/**
	 * Moves the object in the desired direction.
	 * @param dir in which the movement is performed.
	 */
	protected void performMovement(Move dir)
	{
		this.pos = this.pos.move(dir);
	}

	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();

	/**
	 * Removes the object.
	 */
	public void onDelete() {
		this.game.removeObject(this);
	}

	/**
	 * Performs the automatic move of the object.
	 */
	public void automaticMove() {
		this.performMovement(this.dir);
	}
	public void computerAction() {}

    /**
	 * Performs attack on another GameItem
	 * @param other GameItem
	 * @return true if collision
	 */
	@Override
	public boolean performAttack(GameItem other) {return false;}

	/**
	 * Handles events after GameItem has been hit by a Weapon
	 * This method is specific for attacks on player
	 * @param weapon EnemyWeapon
	 * @return always true
	 */
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {return false;}

	/**
	 * Handles events after GameItem has been hit by a Weapon
	 * This method is specific for attacks on aliens or their weapons
	 * @param weapon UCMWeapon
	 * @return always true
	 */
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {return false;}

	@Override
	public String toString() {
		return this.getSymbol();
	}

	/**
	 * @param Position of another object.
	 * @return true if the object is in the same position as the parameter position.
	 */
	@Override
	public boolean isOnPosition(Position pos) {
		return this.pos.equals(pos);
	}
}
