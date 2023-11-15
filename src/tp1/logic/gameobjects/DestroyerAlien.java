package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * Class containing all the attributes and methods of the destroyer aliens. <br>
 * Also, this class extends the abstract class of Alien.
 */
public class DestroyerAlien extends AlienShip
{

    public DestroyerAlien(Game game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public boolean isOnPosition(Position pos) {
        return false;
    }

    @Override
    protected String getSymbol() {
        return null;
    }

    @Override
    protected int getDamage() {
        return 0;
    }

    @Override
    protected int getArmour() {
        return 0;
    }

    @Override
    public void onDelete() {

    }

    @Override
    public void automaticMove() {

    }
}
