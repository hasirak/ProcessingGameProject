package backend.actor;

import backend.main.Vector;
import backend.shipmodule.ShipModule;
import userinterface.Drawable;

/**
 * A simple bullet. Travels straight towards the target with no acceleration.
 *
 * @author Kristian Honningsvag.
 */
public class Bullet extends Projectile implements Drawable {

    // Color.
    private int[] bulletRGBA = new int[]{180, 150, 0, 255};

    /**
     * Constructor.
     */
    public Bullet(Vector position, ShipModule shipModule) {

        super(position, shipModule);

        name = "Bullet";
        hitBoxRadius = 4;
        currentHitPoints = 1;
        mass = 2;
        collisionDamageToOthers = shipModule.getProjectileDamage();

        setLaunchVelocity(shipModule.getOwner().getHeading());
    }

    @Override
    public void act(double timePassed) {
        super.addFriction();
        super.calcAcceleration();
        super.calcSpeed(timePassed);
        super.updatePosition(timePassed);
        checkWallCollisions(timePassed);
        checkActorCollisions(timePassed);
    }

    @Override
    public void draw() {
        guiHandler.strokeWeight(0);
        guiHandler.stroke(bulletRGBA[0], bulletRGBA[1], bulletRGBA[2]);
        guiHandler.fill(bulletRGBA[0], bulletRGBA[1], bulletRGBA[2]);
        guiHandler.ellipse((float) this.getPosition().getX(), (float) this.getPosition().getY(), (float) hitBoxRadius * 2, (float) hitBoxRadius * 2);
    }

    @Override
    public void die() {
        gameEngine.getCurrentLevel().getProjectiles().remove(this);
    }

    /**
     * Sets the projectiles speedT vectors.
     *
     * @param heading The direction the actor is aiming.
     */
    private void setLaunchVelocity(double heading) {
        this.getSpeedT().set(shipModule.getLaunchVelocity() * Math.cos(heading), shipModule.getLaunchVelocity() * Math.sin(heading), 0);
    }

}
