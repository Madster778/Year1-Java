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

// Creates a class for the barricade collisions
public class BarricadeCollision implements CollisionListener {

    // Creates a private variable for barricade
    private Barricade barricade;
    // A constructor to set the private variable barricade to ba
    public BarricadeCollision(Barricade ba){ this.barricade = ba; }

    private static SoundClip crashSound;
    static {
        try {
            crashSound = new SoundClip("data/Crash Sound Effect.wav");
            crashSound.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // A constructor to have the barricades collide and destroy the vehicle
    // A collision for static bodies, to destroy barricades when they touch a wall
    // If two barricades collide with each other, one gets destroyed destroyed
    // When a laser collides with a barricade, the linear velocity and angular velocity is reset
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Driver) {
            crashSound.play();
            ((Driver) e.getOtherBody()).decreaseLives();
            barricade.destroy();
        } else if (e.getOtherBody() instanceof StaticBody) {
            barricade.destroy();
        } else if (e.getOtherBody() instanceof Barricade) {
            e.getOtherBody().destroy();
        } else if (e.getOtherBody() instanceof FireTruck) {
            e.getOtherBody().destroy();
        }else if (e.getOtherBody() instanceof Laser){
            e.getOtherBody().destroy();
            barricade.setLinearVelocity(new Vec2(0, -10));
            barricade.setAngleDegrees(0);
            barricade.setAngularVelocity(0);
        }
    }
}
