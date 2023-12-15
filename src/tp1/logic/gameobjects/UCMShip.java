package tp1.logic.gameobjects;

import tp1.control.exceptions.LaserInFlightException;
import tp1.control.exceptions.NotAllowedMoveException;
import tp1.control.exceptions.NotEnoughPointsException;
import tp1.control.exceptions.OffWorldException;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class of the UCMShip that contains all the attributes and methods.
 */
public class UCMShip extends Ship{
    //Weapons of the UCMShip.
    private UCMLaser laser;
    private SuperLaser superLaser;
    private Shockwave shockwave;
    private static final Move[] possibleMoves = {Move.LLEFT, Move.LEFT, Move.NONE, Move.RIGHT, Move.RRIGHT};

    /**
     * Constructor for the UCMShip.
     */
    public UCMShip(GameWorld game, Position position) {
        super(game, position, 3);
        this.dir = Move.NONE;
        this.armor = 0;
    }

    public static String allowedMoves(String s) {
        List<String> possibleMovesStrings = Arrays.stream(possibleMoves).map(m -> m.toString()).toList();
        return String.join(s, possibleMovesStrings);
    }

    /**
     * Performs the movement of the ship with the direction provided by the user.
     * @param move given by the input of the user.
     */
    public void move(Move move) throws OffWorldException, NotAllowedMoveException {
        if(!Arrays.asList(UCMShip.possibleMoves).contains(move))
            throw new NotAllowedMoveException();

        this.pos.validMove(move);
        this.dir = move;
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
    public void shootLaser() throws LaserInFlightException {
        if(isAttacking()) throw new LaserInFlightException();

        //UCMLaser is created and added to the object container.
        UCMLaser laser = new UCMLaser(this.game, this);
        this.laser = laser;
        this.game.addObject(laser);
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
    public void shootSuperLaser() throws LaserInFlightException {
        if(isAttacking()) throw new LaserInFlightException();

        //Creates the SuperLaser and adds it to the Object Container.
        SuperLaser laser = new SuperLaser(this.game, this);
        this.superLaser = laser;
        this.game.addObject(laser);
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
