package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
    protected UCMShip ship;

    public UCMWeapon(Game game, Position pos, int life) {
        super(game, pos, life);
    }

    @Override
    public void computerAction() {
        // next position is out of bounds? -> delete weapon
        if(!this.game.inBoundsY(this.pos.move(this.dir))) this.onDelete();

        boolean collision = this.game.attackEnemy(this);
        // weapon collided with anything? -> delete weapon
        if(collision) this.onDelete();
    }

    @Override
    public boolean performAttack(GameItem other) {
        if(!other.isOnPosition(this.pos)) return false;
        if(!(other instanceof EnemyWeapon || other instanceof EnemyShip)) return false;

        return other.receiveAttack(this);
    }
}
