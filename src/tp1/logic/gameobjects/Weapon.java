package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
    public Weapon(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }
    public void computerAction() {}
    public void collision() {
        if(--this.life == 0) this.onDelete();
    }

}
