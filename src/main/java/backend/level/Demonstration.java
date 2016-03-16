package backend.level;

import backend.main.GameEngine;
import backend.actor.Player;
import backend.main.FadingCanvasItemManager;
import backend.main.RocketManager;
import backend.main.Vector;

/**
 * Demonstration level. Made specifically for the presentation.
 *
 * @author Kristian Honningsvag.
 */
public class Demonstration extends Level {

    /**
     * Constructor.
     */
    public Demonstration(GameEngine gameEngine, RocketManager rocketManager, FadingCanvasItemManager itemManager) {

        super(gameEngine);

        this.rocketManager = rocketManager;
        this.fadingCanvasItems = itemManager;
        levelName = "Demonstration";
        baseTimeToNextWave = 0;

        player = new Player(new Vector(150, 300, 0), gameEngine);
        actors.add(player);
    }

    @Override
    public void nextWave() {

        if (!onLastWave) {

            timeToNextWave = baseTimeToNextWave - timer.timePassed();

            if (timeToNextWave <= 0) {

                currentWave++;

                switch (currentWave) {

                    case 1: {
                        actorSpawner.spawnRocketLauncher(150, 300);
                        actorSpawner.spawnSeekerLauncher(150, 300);
                        actorSpawner.spawnLaser(150, 300);
                        actorSpawner.spawnEMP(150, 300);

                        actorSpawner.spawnCarrier(1);
                        actorSpawner.spawnFrigate(2);
                        actorSpawner.spawnKamikazeDrone(1);
                        setTimeToNextWave(12000);
                        break;
                    }

                    case 2: {
                        actorSpawner.spawnFrigate(3);
                        actorSpawner.spawnKamikazeDrone(1);
                        setTimeToNextWave(9000);
                        break;
                    }

                    case 3: {
                        actorSpawner.spawnFrigate(3);
                        actorSpawner.spawnKamikazeDrone(1);
                        setTimeToNextWave(9000);
                        break;
                    }

                    case 4: {
                        actorSpawner.spawnFrigate(3);
                        actorSpawner.spawnKamikazeDrone(1);
                        onLastWave = true;
                        break;
                    }
                }
            }
        }
    }

}
