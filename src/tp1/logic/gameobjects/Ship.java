package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Ship extends GameObject {

    protected int armor = 0;
    public Ship(GameWorld game, Position pos, int life) {
        super(game, pos, life);

    }

    public Ship() {

    }

    protected void dealDamage(Weapon weapon) {
        this.life -= weapon.getDamage();

        // delete ship if health is 0
        if(this.life == 0) this.onDelete();

        // delete weapon on collision
        weapon.collision();
    }

}
