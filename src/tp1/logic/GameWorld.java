package tp1.logic;

import tp1.logic.gameobjects.EnemyWeapon;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMWeapon;

public interface GameWorld {
    boolean inBoundsX(Position pos);
    boolean inBoundsY(Position pos);
    boolean performAttack(UCMWeapon weapon);
    boolean performAttack(EnemyWeapon weapon);
    void removeObject(GameObject object);
    void addObject(GameObject object);
    void obtainShockwave();
    void increaseScore(int byScore);

    void decreaseAlienCount();
}
