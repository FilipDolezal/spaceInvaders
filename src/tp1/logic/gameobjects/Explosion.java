package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public class Explosion extends UCMWeapon {

    /**
     * All the positions that are affected by this explosion
     */
    private Position[] positions;

    public Explosion(GameWorld game, Position pos) {
        super(game, pos, 0);
        super.dir = Move.NONE;

        positions = new Position[]{
                pos.move(Move.UP),
                pos.move(Move.UP).move(Move.LEFT),
                pos.move(Move.UP).move(Move.RIGHT),

                pos,
                pos.move(Move.LEFT),
                pos.move(Move.RIGHT),

                pos.move(Move.DOWN),
                pos.move(Move.DOWN).move(Move.LEFT),
                pos.move(Move.DOWN).move(Move.RIGHT)
        };
    }

    @Override
    public boolean performAttack(GameItem other) {
        for (Position position: positions) {
            if(other.isOnPosition(position))
                other.receiveAttack(this);
        }

        return false;
    }

    @Override
    protected String getSymbol() {
        return "";
    }

    @Override
    protected int getDamage() {
        return 1;
    }

    @Override
    protected int getArmour() {
        return 0;
    }
}
