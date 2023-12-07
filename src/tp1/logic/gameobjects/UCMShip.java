package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * Class of the UCMShip that contains all the attributes and methods.
 */
public class UCMShip extends Ship{
    //Weapons of the UCMShip.
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

    /**
     * Performs the movement of the ship with the direction provided by the user.
     * @param move given by the input of the user.
     * @return true if the UCMShip could perform the movement, false otherwise.
     */
    public boolean move(Move move) {
        if(!this.game.inBoundsX(this.pos.move(move)))
            return false;

        this.dir = move;
        return true;
    }

    /**
     * Method to check if the UCMLaser or the SuperLaser has been shot.
     * @return true if one of the weapons is active, false otherwise.
     */
    private boolean isAttacking() {
        return laser != null || superLaser != null;
    }

    /**
     * Performs the shoot of the UCMLaser.
     * @return true if it was successfully shot, false otherwise.
     */
    public boolean shootLaser() {
        if(isAttacking()) return false; //If the SuperLaser is active, then the UCMLaser can't be shot.
        //UCMLaser is created and added to the object container.
        UCMLaser laser = new UCMLaser(this.game, this);
        this.laser = laser;
        this.game.addObject(laser);
        return true;
    }

    /**
     * Disables the UCMLaser when called.
     */
    public void disableLaser() {
        this.laser = null;
    }

    /**
     * Performs the shooting of the SuperLaser.
     * @return true if the SuperLaser was successfully shot, false otherwise.
     */
    public boolean shootSuperLaser() {
        if(isAttacking()) return false; //If the UCMLaser is active, then the SuperLaser can't be shot.
        if(!game.canShootSuperLaser()) return false; //If the player has not enough points, the SuperLaser can't be shot.
        //Creates the SuperLaser and adds it to the Object Container.
        SuperLaser laser = new SuperLaser(this.game, this);
        this.superLaser = laser;
        this.game.addObject(laser);
        return true;
    }

    /**
     * Disables the SuperLaser.
     */
    public void disableSuperLaser() {
        this.superLaser = null;
    }

    /**
     * Deletes the Shockwave.
     */
    public void deleteShockwave() {
        this.shockwave = null;
    }

    public Shockwave getShockwave() {
        return this.shockwave;
    }

    /**
     * Creates a new Shockwave.
     */
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
        this.dealDamage(weapon);    //Receives an attack from a bomb or any enemy weapon.
        return true;
    }
}
