package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class Weapon extends GameObject{
    public Weapon(Game game, Position pos, int life) {
        super(game, pos, life);
    }
}
