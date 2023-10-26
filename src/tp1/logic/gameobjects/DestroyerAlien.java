package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class DestroyerAlien extends Alien
{
    public Bomb bomb;
    public DestroyerAlien(AlienManager alienManager, Position position) {
        super(alienManager, position);
        this.dir = Move.RIGHT;
        this.symbol = 'D';
    }
    public void preformAttack(Game game) {
        if(bomb == null) {
            bomb = new Bomb(position, Move.DOWN, game);
        }
    }

    public Bomb getBomb(){
        return this.bomb;
    }

    public void DisableBomb(){
        this.bomb = null;
    }

}
