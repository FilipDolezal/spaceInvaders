package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMWeapon;

public interface GameWorld {
    public boolean inBoundsX(Position pos);
    public boolean inBoundsY(Position pos);
    public boolean attackEnemy(UCMWeapon ucmWeapon);
    public void removeObject(GameObject object);
    public void addObject(GameObject object);
}
