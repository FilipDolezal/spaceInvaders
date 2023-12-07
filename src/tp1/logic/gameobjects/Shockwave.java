package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public class Shockwave extends UCMWeapon {
    /**
     * Constructor of the Shockwave
     * @param game Interface with all the methods related to functionality of the objects.
     * @param ship Takes as parameter the UCMShip because the shockwave is related to the ship.
     */
    public Shockwave(GameWorld game, UCMShip ship) {
        super(game, new Position(-1, -1), 1);

        super.ship = ship;
    }

    @Override
    protected String getSymbol() {
        return null;
    }

    @Override
    protected int getDamage() {
        return 1;
    }

    @Override
    protected int getArmour() {
        return 0;
    }

    /**
     * Deletes the shockwave from the ship when it is used.
     */
    @Override
    public void onDelete() {
        this.ship.deleteShockwave();
    }

    @Override
    public void computerAction() {}

    /**
     * Performs the attack over all the ships
     * @param other GameItem
     * @return True if it has been successfully performed, false otherwise.
     */
    @Override
    public boolean performAttack(GameItem other) {
        return other.receiveAttack(this);
    }
    @Override
    public void automaticMove() {}
}
