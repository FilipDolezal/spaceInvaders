package tp1.logic;

import tp1.logic.gameobjects.EnemyWeapon;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMWeapon;

public interface GameWorld {
    boolean inBoundsX(Position pos);
    boolean inBoundsY(Position pos);
    boolean attackEnemy(UCMWeapon ucmWeapon);
    boolean attackPlayer(EnemyWeapon enemyWeapon);
    void removeObject(GameObject object);
    void addObject(GameObject object);
    void obtainShockwave();
}
