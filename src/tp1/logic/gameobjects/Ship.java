package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Ship extends GameObject {

    protected int armor = 0;

    /**
     * Constructor of the Ship.
     * @param game Interface with all the methods with the functionality.
     * @param pos Position of the ship
     * @param life Life of the ship
     */
    public Ship(GameWorld game, Position pos, int life) {
        super(game, pos, life);

    }

    public Ship() {

    }
    /*
    Method when a ship receives an attack and subtracts life from the ship.
     */
    protected void dealDamage(Weapon weapon) {
        this.life -= weapon.getDamage();

        // delete ship if health is 0
        if(this.life <= 0) this.onDelete();

        // delete weapon on collision
        weapon.collision();
    }

}
