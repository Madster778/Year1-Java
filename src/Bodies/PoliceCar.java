package Bodies;

import Collisions.PoliceCarCollision;
import Levels.GameLevel;
import city.cs.engine.*;
import game.DriverController;
import game.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Creates a class for the police car body
public class PoliceCar extends DynamicBody {

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

    // Provides the hitbox for the police car body
    private static final Shape policeCarShape = new PolygonShape(
            -1.5f,-2.87f,
            -1.48f,2.8f,
            -0.85f,3.43f,
            0.85f,3.44f,
            1.44f,2.86f,
            1.48f,-2.83f,
            1.02f,-3.4f,
            -1.11f,-3.4f);

    // Sets the police car body image to the image of the police car png
    private static final BodyImage image = new BodyImage("data/policecar.png", 7);

    // Creates a private variable for health
    private int health;

    // A constructor for police car to add the image and health
    public PoliceCar(GameLevel w) {
        super(w, policeCarShape);
        world = w;
        addImage(image);
        health = 6;
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

    // A method used to allow the police cars to respawn when each of the police cars are destroyed
    @Override
    public void destroy() {
        PoliceCar policecar = new PoliceCar(world);
        policecar.setPosition(new Vec2(world.getRoadPosition(), 30));
        policecar.setLinearVelocity(new Vec2(0, -10));
        PoliceCarCollision policecarCollision = new PoliceCarCollision(policecar);
        policecar.addCollisionListener(policecarCollision);

        super.destroy();

        GameLevel world = (GameLevel)this.getWorld();
        if (world.isComplete()){
            world.getGame().goToNextLevel();
        }

    }
}