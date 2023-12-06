package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * Class of the UCMShip that contains all the attributes and methods.
 */
public class UCMShip extends Ship{

    private UCMLaser laser;
    private SuperLaser superLaser;
    private Shockwave shockwave;

    /**
     * Constructor for the UCMShip.
     */
    public UCMShip(GameWorld game, Position position) {
        super(game, position, 3);
        this.dir = Move.NONE;
        this.armor = 0;
    }

    public boolean move(Move move) {
        if(!this.game.inBoundsX(this.pos.move(move)))
            return false;

        this.dir = move;
        return true;
    }

    private boolean isAttacking() {
        return laser != null || superLaser != null;
    }

    public boolean shootLaser() {
        if(isAttacking()) return false;

        UCMLaser laser = new UCMLaser(this.game, this);
        this.laser = laser;
        this.game.addObject(laser);
        return true;
    }
    public void disableLaser() {
        this.laser = null;
    }

    public boolean shootSuperLaser() {
        if(isAttacking()) return false;
        if(!game.canShootSuperLaser()) return false;

        SuperLaser laser = new SuperLaser(this.game, this);
        this.superLaser = laser;
        this.game.addObject(laser);
        return true;
    }
    public void disableSuperLaser() {
        this.superLaser = null;
    }

    public void deleteShockwave() {
        this.shockwave = null;
    }

    public Shockwave getShockwave() { return this.shockwave; }

    public void obtainShockwave() {
        this.shockwave = new Shockwave(this.game,this);
    }

    @Override
    protected String getSymbol() {
        return this.isAlive() ? Messages.UCMSHIP_SYMBOL: Messages.UCMSHIP_DEAD_SYMBOL;
    }

    @Override
    protected int getDamage() {
        return 0;
    }

    @Override
    protected int getArmour() {
        return this.armor;
    }

    @Override
    public void onDelete() {
        //not used?¿
    }

    public void automaticMove() {
        super.automaticMove();

        // remove direction after each move
        this.dir = Move.NONE;
    }

    @Override
    public boolean receiveAttack(EnemyWeapon weapon) {
        this.dealDamage(weapon);
        return true;
    }
}
