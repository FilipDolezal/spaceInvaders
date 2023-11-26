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
    private Bomb bomb;

    public DestroyerAlien(Game game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    protected String getSymbol() {
        return Messages.DESTROYER_ALIEN_SYMBOL;
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
    public void computerAction() {
        if(this.bomb == null) {
            Bomb bomb = new Bomb(
                    this.game,
                    this.pos,
                    1
            );

            this.bomb = bomb;
            this.game.addObject(bomb);
        }
    };
}
