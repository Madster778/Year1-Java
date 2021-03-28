package Collisions;

import Bodies.*;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Creates a class for the blockade collisions
public class BlockadeCollision implements CollisionListener {

    // Creates a private variable for blockade
    private Blockade blockade;
    // A constructor to set the private variable blockade to bl
    public BlockadeCollision(Blockade bl){ this.blockade = bl; }

    private static SoundClip crashSound;
    static {
        try {
            crashSound = new SoundClip("data/Crash Sound Effect.wav");
            crashSound.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // A constructor to have the blockades collide and destroy the vehicle
    // A collision for static bodies, to destroy blockades when they touch a wall
    // When a blockade collides with the driver, the driver loses all 3 lives, and the crashSound will be called
    // If two blockades collide with each other, one gets destroyed destroyed
    // When a laser collides with a blockades, the linear velocity and angular velocity is reset
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Driver) {
            crashSound.play();
            blockade.destroy();
            ((Driver) e.getOtherBody()).decreaseLives();
            ((Driver) e.getOtherBody()).decreaseLives();
            ((Driver) e.getOtherBody()).decreaseLives();
            ((Driver) e.getOtherBody()).decreaseLives();
            ((Driver) e.getOtherBody()).decreaseLives();
        } else if (e.getOtherBody() instanceof StaticBody) {
            blockade.destroy();
        } else if (e.getOtherBody() instanceof Blockade) {
            e.getOtherBody().destroy();
        } else if (e.getOtherBody() instanceof OffRoadCar) {
            e.getOtherBody().destroy();
        }else if (e.getOtherBody() instanceof Laser){
            e.getOtherBody().destroy();
            blockade.setLinearVelocity(new Vec2(0, -10));
            blockade.setAngleDegrees(0);
            blockade.setAngularVelocity(0);
        }
    }
}
