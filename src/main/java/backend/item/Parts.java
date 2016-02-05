package backend.item;

import backend.actor.Actor;
import backend.main.GameEngine;
import backend.main.Vector;
import userinterface.Drawable;

/**
 * Parts dropped by defeated enemies. Used to upgrade weapon modules.
 *
 * @author Kristian Honningsvag.
 */
public class Parts extends Item implements Drawable {

    // Color.
    private int[] bodyRGBA = new int[]{240, 240, 10, 255};

    /**
     * Constructor.
     */
    public Parts(Vector position, GameEngine gameEngine) {

        super(position, gameEngine);

        hitBoxRadius = 5;
    }

    @Override
    public void draw() {
        // Draw main body.
        guiHandler.strokeWeight(0);
        guiHandler.stroke(bodyRGBA[0], bodyRGBA[1], bodyRGBA[2]);
        guiHandler.fill(bodyRGBA[0], bodyRGBA[1], bodyRGBA[2]);
        guiHandler.ellipse((float) this.getPosition().getX(), (float) this.getPosition().getY(), (float) hitBoxRadius * 2, (float) hitBoxRadius * 2);
    }

    @Override
    public Object pickup(Actor looter) {
        currentHitPoints = 0;
        return this;
    }

    @Override
    public void die() {
        gameEngine.getCurrentLevel().getItems().remove(this);
    }

}
