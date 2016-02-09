package backend.level;

import backend.actor.AI;
import backend.actor.Enemy;
import backend.actor.Frigate;
import backend.actor.SimpleAI;
import backend.actor.ExtraAI;
import backend.actor.SlayerAI;
import backend.main.Vector;
import java.util.Random;

/**
 * Handles spawning enemies.
 *
 * @author Kristian Honningsvag.
 */
public class ActorSpawner {

    Level currentLevel;
    
    /**
     * Constructor.
     *
     * @param currentLevel The current level.
     */
    public ActorSpawner(Level currentLevel) {
        this.currentLevel = currentLevel;
        
    }

    /**
     * Spawns a given number of frigates at random locations.
     *
     * @param amount Number of frigates to spawn.
     */
    public void spawnFrigate(int amount) {

        Random random = new Random();
        for (int i = 0; i < amount; i++) {

            int randX = random.nextInt(currentLevel.getGameEngine().getGuiHandler().getWidth() - 200) + 100;
            int randY = random.nextInt(currentLevel.getGameEngine().getGuiHandler().getHeight() - 200) + 100;

            
            Enemy enemy = new Frigate(new Vector(randX, randY, 0), currentLevel.getGameEngine());
            AI ai;
            if(Math.random()<=0.3){
                ai = new ExtraAI(currentLevel.getGameEngine(), currentLevel.getPlayer(), enemy);
            }else if(Math.random()<=0.6){
                ai = new SlayerAI(enemy, currentLevel.getPlayer());
            }else{
                ai = new SimpleAI(currentLevel.getGameEngine(), currentLevel.getPlayer(), enemy);
            }
            enemy.setAI(ai);
            
            currentLevel.getGameEngine().getCurrentLevel().getEnemies().add(enemy);
            currentLevel.getGameEngine().getCurrentLevel().getActors().add(enemy);
        }
    }

}
