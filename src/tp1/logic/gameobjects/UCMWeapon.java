package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
    protected UCMShip ship;

    public UCMWeapon(GameWorld game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public void computerAction() {
        // next position is out of bounds? -> delete weapon
        if(!this.game.inBoundsY(this.pos.move(this.dir))) this.onDelete();

        boolean collision = this.game.performAttack(this);
        // weapon collided with anything? -> delete weapon
        if(collision) this.onDelete();
    }

    @Override
    public boolean performAttack(GameItem other) {
        if(!(other.isOnPosition(this.pos) || other.isOnPosition(this.pos.move(this.dir)))) return false;
        return other.receiveAttack(this);
    }
}
