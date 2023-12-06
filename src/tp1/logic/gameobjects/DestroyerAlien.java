package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * Class containing all the attributes and methods of the destroyer aliens. <br>
 * Also, this class extends the abstract class of Alien.
 */
public class DestroyerAlien extends AlienShip
{
    private Bomb bomb;

    /**
     * Constructor of the Destroyer Aliens.
     * @param game
     * @param pos
     * @param alienManager
     */
    public DestroyerAlien(GameWorld game, Position pos, AlienManager alienManager) {
        super(game, pos, alienManager);
        super.score = 10;
        super.life = 1;
    }

    public DestroyerAlien() {

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

    /**
     * ComputerAction checks if the bomb is
     */
    @Override
    public void computerAction() {
        super.computerAction();
        if(this.bomb == null && this.game.tryFiringChance()) {  //If the bomb is null and random true then create a new bomb.
            Bomb bomb = new Bomb(
                    this.game,
                    this,
                    this.pos,
                    1
            );

            this.bomb = bomb;
            this.game.addObject(bomb);  //the bomb is added to the container
        }
    }

    /**
     * Make a copy of the DestroyerAlien list.
     * @param game
     * @param pos
     * @param am
     * @return the copy of DestroyerAliens
     */
    @Override
    protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
        return new DestroyerAlien(game, pos, am);
    }

    /**
     * Deletes the bomb
     */
    public void deleteBomb() {
        this.bomb = null;
    }
}
