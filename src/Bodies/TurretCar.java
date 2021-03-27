package Bodies;

import Collisions.LaserCollision;
import Collisions.TurretCarCollision;
import Levels.GameLevel;
import city.cs.engine.*;
import game.DriverController;
import game.Game;
import game.TurretCarHandler;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Creates a class for the tank body
public class TurretCar extends DynamicBody {

    // Creates a private variable world for the game world
    private GameLevel world;

    private Game game;

    private TurretCar turretCar;

    // Creates a static variable for explosionSound which holds a SoundClip
    private static SoundClip explosionSound;
    static {
        try {
            explosionSound = new SoundClip("data/Explosion Sound Effect.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // Provides the hitbox for the tank body
    private static final Shape turretcarShape = new PolygonShape(
            -3.93f,5.04f,
            -3.88f,-3.82f,
            -0.95f,-5.5f,
            0.94f,-5.5f,
            3.82f,-3.86f,
            3.87f,5.04f,
            0.8f,5.52f,
            -0.88f,5.54f);

    // Sets the tank body image to the image of the tank png
    private static final BodyImage image = new BodyImage("data/turretcar.png", 12f);

    // Creates a private variable for health
    private int health;

    // A constructor for tank to add the image and health
    public TurretCar(GameLevel w) {
        super(w, turretcarShape);
        world = w;
        addImage(image);
        health = 12;
    }

    // A method used to decrease the health of the tank and destroy it when at 0 health
    public void decreaseHealth() {
        health--;
        if (health < 1) {
            Driver.setKills();
            DriverController.setScore();
            this.destroy();
            explosionSound.play();
        }
    }

//    public void shooting() {
//        Vec2 selfPoint = turretCar.getPosition().add(new Vec2(0, -2));
//        Laser laser = new Laser(turretCar.getWorld());
//        laser.setPosition(selfPoint);
//        laser.setLinearVelocity(new Vec2(0, -50));
//        LaserCollision laserCollision = new LaserCollision(laser);
//        laser.addCollisionListener(laserCollision);
//    }


    // A method used to allow the tanks to respawn when each of the tanks are destroyed
    @Override
    public void destroy() {
        TurretCar turretCar = new TurretCar(world);
        turretCar.setPosition(new Vec2(world.getRoadPosition(), 30));
        turretCar.setLinearVelocity(new Vec2(0, -10));
        TurretCarCollision turretCarCollision = new TurretCarCollision(turretCar);
        turretCar.addCollisionListener(turretCarCollision);
        super.destroy();
        GameLevel world = (GameLevel)this.getWorld();
        if (world.isComplete()){
            world.getGame().goToNextLevel();
        }

        Game.getLevel().setTurretCar(turretCar);

    }
}