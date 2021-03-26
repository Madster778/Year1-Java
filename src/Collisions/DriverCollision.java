package Collisions;

import Bodies.*;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Creates a class for the driver collision
public class DriverCollision implements CollisionListener {

    // Creates a private variable for driver
    private Driver driver;

    // A constructor to set the private variable driver to d
    public DriverCollision(Driver d) {
        this.driver = d;
    }

    // Creates a static variable for crashSound which holds a SoundClip
    private static SoundClip crashSound;
    static {
        try {
            crashSound = new SoundClip("data/Crash Sound Effect.wav");
            crashSound.setVolume(0.2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // A constructor to have the driver collide with vehicle bodies and decrement lives by 1
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof FireTruck) {
            driver.decreaseLives();
            e.getOtherBody().destroy();
            crashSound.play();
        } else if (e.getOtherBody() instanceof OffRoadCar) {
            driver.decreaseLives();
            e.getOtherBody().destroy();
            crashSound.play();
        } else if (e.getOtherBody() instanceof PoliceCar) {
            driver.decreaseLives();
            e.getOtherBody().destroy();
            crashSound.play();
        } else if (e.getOtherBody() instanceof TurretCar) {
            driver.decreaseLives();
            e.getOtherBody().destroy();
            crashSound.play();
        }
    }
}
