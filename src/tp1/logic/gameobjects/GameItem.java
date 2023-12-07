package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
	//Methods related to the functionality of the Game Objects.
	
	public boolean performAttack(GameItem other);
	
	public boolean receiveAttack(EnemyWeapon weapon);
	public boolean receiveAttack(UCMWeapon weapon);

	public boolean isAlive();
	public boolean isOnPosition(Position pos);

}
