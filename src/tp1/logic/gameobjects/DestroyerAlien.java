package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends Alien
{
    private Bomb bomb;
    public static final int SCORE = 10;

    public DestroyerAlien(AlienManager alienManager, Position position) {
        super(alienManager, position);
        this.dir = Move.LEFT;
        this.symbol = Messages.DESTROYER_ALIEN_SYMBOL;
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

    public boolean isBombHit(UCMLaser laser) {
        if(!isBombActive()) return false;
        return laser.performAttack(this.bomb);
    }
}
