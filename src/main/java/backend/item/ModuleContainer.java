package backend.item;

import backend.actor.Actor;
import backend.main.GameEngine;
import backend.main.Vector;
import backend.shipmodule.ShipModule;
import userinterface.Drawable;

/**
 * Container for a power up or ship module that the player can pick up and
 * equip.
 *
 * @author Kristian Honningsvag.
 */
public class ModuleContainer extends Item implements Drawable {

    // Color.
    private int[] bodyRGBA = new int[]{200, 200, 20, 255};

    private ShipModule shipModule;  // Set in construntor.

    /**
     * Constructor.
     */
    public ModuleContainer(Vector position, GameEngine gameEngine, ShipModule shipModule) {

        super(position, gameEngine);

        this.shipModule = shipModule;

        mass = 1;
        hitBoxRadius = 20;
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
        return shipModule;
    }

    @Override
    public void die() {
        gameEngine.getCurrentLevel().getItems().remove(this);
    }

}
