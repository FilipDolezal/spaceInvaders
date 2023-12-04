package tp1.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import tp1.logic.gameobjects.*;

public class GameObjectContainer {
	private List<GameObject>
			objects = new ArrayList<>(),
			deleted = new ArrayList<>();

	public void add(GameObject object) {
		objects.add(object);
	}

	/**
	 * Add object to a list of deleted items.
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
	public void postActions() {
		for(GameObject deleted: this.deleted) {
			this.objects.remove(deleted);
		}
		this.deleted.clear();
	}

	public boolean performAttack(UCMWeapon weapon) {
		for(int i = 0; i < objects.size(); i++ ) {
			GameItem item = objects.get(i);
			if(weapon.performAttack(item)) return true;
		}
		return false;
	}

	public boolean performShockwave(Shockwave shockwave) {
		if(shockwave == null) return false;
		for(int i = 0; i < objects.size(); i++ ) {
			GameItem item = objects.get(i);
			shockwave.performAttack(item);
		}
		return true;
	}

	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		for (GameItem item: this.objects) {
			if(item.isOnPosition(pos)) return item.toString();
		}
		return "";
	}

	public List<GameObject> getObjects() {
		return objects;
	}
}
