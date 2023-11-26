package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMShip;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}

	public void automaticMoves() {
		for (GameObject o: objects) {
			o.automaticMove();

		}
	}

	public void computerActions() {
		for (GameObject o: objects) {
			o.computerAction();
		}
	}

	public List<GameObject> getObjects() {
		return objects;
	}
}
