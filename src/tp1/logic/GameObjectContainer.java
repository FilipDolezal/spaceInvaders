package tp1.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import tp1.logic.gameobjects.*;

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
		for(int i = 0; i < objects.size(); i++ ) {
			objects.get(i).automaticMove();
		}
	}

	public void computerActions() {
		for(int i = 0; i < objects.size(); i++ ) {
			objects.get(i).computerAction();
		}
	}

	private List<EnemyWeapon> getEnemyWeapons() {
		return objects.stream()
				.filter(o -> o instanceof EnemyWeapon)
				.map(o -> (EnemyWeapon) o)
				.collect(Collectors.toList());
	}

	private List<EnemyShip> getEnemyShips() {
		return objects.stream()
				.filter(o -> o instanceof EnemyShip)
				.map(o -> (EnemyShip) o)
				.collect(Collectors.toList());
	}

	public List<GameObject> getObjects() {
		return objects;
	}

	public boolean performAttackOnAliens(UCMWeapon weapon) {
		boolean collision;
		for (EnemyWeapon enemyWeapon: this.getEnemyWeapons()) {
			collision = weapon.performAttack(enemyWeapon);
			if(collision) return true;
		}

		for (EnemyShip enemyShip: this.getEnemyShips()) {
			collision = weapon.performAttack(enemyShip);
			if(collision) return true;
		}
		return false;
	}
}
