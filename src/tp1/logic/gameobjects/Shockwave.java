package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public class Shockwave extends UCMWeapon {

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

    @Override
    public void onDelete() {
        // i have no idea why this function is called after shockwave is performed, but it is???
        this.ship.deleteShockwave();
    }

    @Override
    public void computerAction() {}

    @Override
    public boolean performAttack(GameItem other) {
        if(!(other instanceof AlienShip)) return false;

        return other.receiveAttack(this);
    }

    @Override
    public void automaticMove() {
        //doesnt move
    }
}
