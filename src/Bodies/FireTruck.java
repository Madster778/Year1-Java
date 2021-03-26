package Bodies;

import Collisions.FireTruckCollision;
import Levels.GameLevel;
import city.cs.engine.*;
import game.DriverController;
import game.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Creates a class for the firetruck body
public class FireTruck extends DynamicBody {

    // Creates a private variable world for the game world
    private GameLevel world;

    private Game game;

    // Creates a static variable for explosionSound which holds a SoundClip
    private static SoundClip explosionSound;
    static {
        try {
            explosionSound = new SoundClip("data/Explosion Sound Effect.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // Provides the hitbox for the firetruck body
    private static final Shape fireTruckShape = new PolygonShape(
            -1.74f, -3.95f,
            1.74f, -3.95f,
            1.74f, 3.73f,
            1.44f, 3.97f,
            -1.44f, 3.97f,
            -1.74f, 3.73f);

    // Sets the firetruck body image to the image of the firetruck png
    private static final BodyImage image = new BodyImage("data/firetruck.png", 8);

    // Creates a private variable for health
    private int health;

    // A constructor for firetrucks to add the image and health
    public FireTruck(GameLevel w) {
        super(w, fireTruckShape);
        world = w;
        addImage(image);
        health = 8;
    }

    // A method used to decrease the health of the firetruck and destroy it when at 0 health
    public void decreaseHealth() {
        health--;
        if (health < 1) {
            Driver.setKills();
            DriverController.setScore();
            this.destroy();
            explosionSound.play();
        }

    }

    // A method used to allow the firetrucks to respawn when each of the firetrucks are destroyed
    @Override
    public void destroy() {
        FireTruck firetruck = new FireTruck(world);
        firetruck.setPosition(new Vec2(world.getRoadPosition(), 30));
        firetruck.setLinearVelocity(new Vec2(0, -10));
        FireTruckCollision firetruckCollision = new FireTruckCollision(firetruck);
        firetruck.addCollisionListener(firetruckCollision);

        super.destroy();

        GameLevel world = (GameLevel)this.getWorld();
        if (world.isComplete()){
            world.getGame().goToNextLevel();
        }

    }
}
