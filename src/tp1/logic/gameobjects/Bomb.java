package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public class Bomb {
    private Move dir;
    Position position;
    private Game game;

    public Bomb(Position position, Move dir, Game game) {
        this.game = game;
        this.position = position;
        this.dir = dir;
    }

    /*public void onDelete() {
        game.disableBomb();
    }
    private void die() {
        onDelete();
    }


    private void performMovement(Move dir) {
        this.position = position.move(dir);
    }
    private boolean isOut() {
        return this.game.isOutOfBoundY(this.position);
    }
    public boolean weaponAttack(UCMShip ship) {
        boolean isHit = this.position
                .move(dir)
                .equals(ship.position);

        if(isHit) {
            this.game.alienManager.destroyerAliens.disableBomb();
            ship.receiveAttack();
        }
        return false;
    }

    public void automaticMove () {
        performMovement(dir);
        if(isOut())
            die();
    }
    public Position getPosition() {
        return position;
    }*/
}
