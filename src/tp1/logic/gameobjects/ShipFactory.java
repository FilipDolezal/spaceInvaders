package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;

import java.util.Arrays;
import java.util.List;

public class ShipFactory {
    private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
            new RegularAlien(),
            new DestroyerAlien(),
            new ExplosiveShip()
    );

    public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am) {
        AlienShip ship = AVAILABLE_ALIEN_SHIPS.stream()
                .filter(s -> s.getSymbol().equals(input))
                .findFirst().get();

        return ship.copy(game, pos, am);
    }
}
