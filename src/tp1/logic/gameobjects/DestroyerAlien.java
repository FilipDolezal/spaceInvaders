package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class DestroyerAlien extends Alien
{
    private Bomb bomb;
    public DestroyerAlien(AlienManager alienManager, Position position) {
        super(alienManager, position);
        this.dir = Move.RIGHT;
        this.symbol = 'D';
    }

    /**
     *  Implements the automatic movement of the destroyer alien and its bomb
     */
    @Override
    public void automaticMove() {
        super.automaticMove();
    }

    public void moveBomb() {
        if(isBombActive()) bomb.automaticMove();
    }

    public void enableBomb() {
        if(bomb != null) return;

        bomb = new Bomb(this);
    }

    public void disableBomb(){
        this.bomb = null;
    }

    public boolean performAttack(UCMShip ship) {
        return bomb.performAttack(ship);
    }

    public Position getBombPosition() {
        return bomb.getPosition();
    }

    public boolean isBombActive() { return this.bomb != null; }
}
