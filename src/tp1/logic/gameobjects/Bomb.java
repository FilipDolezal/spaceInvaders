package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class Bomb {
    public static final String SYMBOL = "*";

    private final Move dir = Move.DOWN;
    private DestroyerAlien alien;
    private Position position;

    public Bomb(DestroyerAlien alien) {
        this.alien = alien;
        this.position = alien.getPosition().move(dir);
    }

    public void onDelete() {
        alien.disableBomb();
    }

    private void die() {
        onDelete();
    }

    private void performMovement(Move dir) {
        this.position = position.move(dir);
    }
    private boolean isOut() {
        return Game.isOutOfBoundY(this.position);
    }

    public Position getPosition() { return this.position; }

    /**
     * Method that implements the attack by the bomb to UCMShip.
     * It checks whether both objects are alive and in the same position.
     * If so call the "actual" attack method {@link weaponAttack}.
     * @param ship the UCMShip possibly under attack
     * @return <code>true</code> if the ship has been attacked by the laser.
     */
    public boolean performAttack(UCMShip ship) {
        boolean isHit = this.position
                .move(dir)
                .equals(ship.getPosition());

        return isHit ? this.weaponAttack(ship): false;
    }

    /**
     *
     * @param ship UCMShip under attack by the bomb
     * @return always returns <code>true</code>
     */
    private boolean weaponAttack(UCMShip ship) {
        alien.disableBomb();
        ship.receiveAttack();
        return true;
    }

    public void automaticMove () {
        performMovement(dir);
        if(isOut()) die();
    }
}
