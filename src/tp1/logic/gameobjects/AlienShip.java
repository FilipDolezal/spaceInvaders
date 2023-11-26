package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
    public AlienShip(Game game, Position pos, int life) {
        super(game, pos, life);
        this.dir = Move.LEFT;
    }

    @Override
    public String toString() {
        return this.getSymbol() + "[" + this.getLife() + "]";
    }
}
