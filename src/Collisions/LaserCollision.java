package Collisions;

import Bodies.*;
import Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import game.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Creates a class for the laser collisions
public class LaserCollision implements CollisionListener {

    private GameLevel level;
    private Game game;

    // Creates a private variable for laser
    private Laser laser;

    // A constructor to set the private variable laser to l
    public LaserCollision(Laser l) {
        this.laser = l;
    }

    // Creates a static variable for collisionSound which holds a SoundClip
    private static SoundClip collisionSound;
    static {
        try {
            collisionSound = new SoundClip("data/Collision Sound Effect.wav");
            collisionSound.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // A constructor to have the lasers collide and decrease the vehicle health
    // When a laser collision occurs with a dynamic body, the collisionSound is called
    // The vehicle is destroyed when it reaches 0 health
    // A collision for static bodies, to destroy lasers when they touch a wall
    // if two lasers collide with each other, both are destroyed
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof FireTruck) {
            collisionSound.stop();
            collisionSound.play();
            ((FireTruck) e.getOtherBody()).decreaseHealth();
            ((FireTruck) e.getOtherBody()).setLinearVelocity(new Vec2(0, -10));
            laser.destroy();
        } else if (e.getOtherBody() instanceof OffRoadCar) {
            collisionSound.stop();
            collisionSound.play();
            ((OffRoadCar) e.getOtherBody()).decreaseHealth();
            ((OffRoadCar) e.getOtherBody()).setLinearVelocity(new Vec2(0, -10));
            laser.destroy();
        } else if (e.getOtherBody() instanceof PoliceCar) {
            collisionSound.stop();
            collisionSound.play();
            ((PoliceCar) e.getOtherBody()).decreaseHealth();
            ((PoliceCar) e.getOtherBody()).setLinearVelocity(new Vec2(0, -10));
            laser.destroy();
        } else if (e.getOtherBody() instanceof TurretCar){
            collisionSound.stop();
            collisionSound.play();
            ((TurretCar) e.getOtherBody()).decreaseHealth();
            ((TurretCar) e.getOtherBody()).setLinearVelocity(new Vec2(0, -10));
            laser.destroy();
        } else if (e.getOtherBody() instanceof StaticBody) {
            laser.destroy();
        } else if (e.getOtherBody() instanceof Laser) {
            e.getOtherBody().destroy();
            laser.destroy();
        }
    }
}

