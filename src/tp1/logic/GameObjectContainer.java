package tp1.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import tp1.control.exceptions.NoShockWaveException;
import tp1.logic.gameobjects.*;

/**
 * Class that contains all the Objects in the game.
 */
public class GameObjectContainer {
	private List<GameObject>
			objects = new ArrayList<>(),		//List with all the objects in the game.
			deleted = new ArrayList<>();		//List with the objects that are deleted every cycle.

	public void add(GameObject object) {
		objects.add(object);
	}	//Adds the object to the container of Objects.

	/**
	 * Will effectively remove object from objects list by adding object to a list of deleted items.
	 * After each cycle: <br>
	 * 		- object added to this list will be removed form objects <br>
	 * 		- deleted list will be cleared for another cycle <br>
	 * @param object to remove
	 */
	public void remove(GameObject object) {
		deleted.add(object);
	}
	public void computerActions() {
		for (int i=0; i < objects.size(); i++) objects.get(i).computerAction();
	}
	public void automaticMoves() {
		for (int i=0; i < objects.size(); i++) objects.get(i).automaticMove();
	}

	/**
	 * Removes all the objects that are in the Objects list and in the Deleted list and then clears the Deleted list.
	 */
	public void postActions() {
		for(GameObject deleted: this.deleted) {
			this.objects.remove(deleted);
		}
		this.deleted.clear();
	}

	/**
	 * Iterates over all the objects in the Container checking for an attack.
	 * @param weapon
	 * @return true if there's been a collision with an object, false otherwise.
	 */
	public boolean performAttack(UCMWeapon weapon) {
		for(int i = 0; i < objects.size(); i++ ) {
			GameItem item = objects.get(i);
			if(weapon.performAttack(item)) return true;	//Returns true if there's a collision.
		}
		return false;
	}

	/**
	 * Performs the shockwave over all the Aliens except the Ufo.
	 * @param shockwave
	 * @return true if the shockwave has been successfully executed, false otherwise.
	 */
	public void performShockwave(Shockwave shockwave) throws NoShockWaveException {
		if(shockwave == null)
			throw new NoShockWaveException();

		for(int i = 0; i < objects.size(); i++ ) {
			GameItem item = objects.get(i);
			shockwave.performAttack(item);	//Subtracts 1 life to all aliens except the UFO.
		}
	}

	/**
	 * Method used to print the Objects in the Position they are in the board.
	 * @param col
	 * @param row
	 * @return the symbol of the object in the position it is located, an empty space otherwise.
	 */
	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		for (GameItem item: this.objects) {
			if(item.isOnPosition(pos)) return item.toString();	//Returns the appearance of the object in that position.
		}
		return "";	//Blank space otherwise.
	}
}
