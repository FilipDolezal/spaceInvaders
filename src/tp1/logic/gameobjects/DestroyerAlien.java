package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Move;
import tp1.logic.Position;

public class DestroyerAlien extends Alien
{
    public DestroyerAlien(AlienManager alienManager, Position position) {
        super(alienManager, position);
        this.dir = Move.RIGHT;
        this.symbol = 'D';
    }
}
