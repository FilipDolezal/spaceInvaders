package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	public Position getPos() {
		return pos;
	}

	protected Position pos;

	protected int life;
	protected GameWorld game;
	protected Move dir;
	
	public GameObject(GameWorld game, Position pos, int life) {
		this.pos = pos;
		this.game = game;
		this.life = life;
	}
	
	@Override
	public boolean isAlive() {
		return this.life > 0;
	}
	public int getLife() {
		return this.life;
	}
	protected void performMovement(Move dir)
	{
		this.pos = this.pos.move(dir);
	}

	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();

	public void onDelete() {
		this.game.removeObject(this);
	}
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

	@Override
	public boolean isOnPosition(Position pos) {
		return this.pos.equals(pos);
	}

	public void setLife(int life) {
		this.life = life;
	}

//	@Override
//	public boolean willBeOnPosition(Position pos) {
//		return this.pos.move(this.dir).equals(pos);
//	}
}
