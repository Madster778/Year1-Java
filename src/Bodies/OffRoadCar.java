package Bodies;

import Collisions.OffRoadCarCollision;
import Levels.GameLevel;
import city.cs.engine.*;
import game.DriverController;
import game.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

// Creates a class for the off-road car body
public class OffRoadCar extends DynamicBody {

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

    // Provides the hitbox for the off-road car body
    private static final Shape offRoadCarShape = new PolygonShape(
            -1.33f,4.1f,
            1.31f,4.1f,
            1.89f,3.13f,
            1.75f,-3.38f,
            0.77f,-4.07f,
            -0.79f,-4.07f,
            -1.75f,-3.42f,
            -1.89f,3.22f);

    // Sets the off-road car body image to the image of the off-road car png
    private static final BodyImage image = new BodyImage("data/offroadcar.png", 9);

    // Creates a private variable for health
    private int health;

    // A constructor for off-road car to add the image and health
    public OffRoadCar(GameLevel w) {
        super(w, offRoadCarShape);
        world = w;
        addImage(image);
        health = 10;
    }

    // A method used to decrease the health of the off-road car and destroy it when at 0 health
    public void decreaseHealth() {
        health--;
        if (health < 1) {
            Driver.setKills();
            DriverController.setScore();
            this.destroy();
            explosionSound.play();
        }
    }

    // A method used to allow the off-road cars to respawn when each of the off-road cars are destroyed
    @Override
    public void destroy() {
        OffRoadCar offroadcar = new OffRoadCar(world);
        offroadcar.setPosition(new Vec2(world.getRoadPosition(), 30));
        offroadcar.setLinearVelocity(new Vec2(0, -10));
        OffRoadCarCollision offroadcarCollision = new OffRoadCarCollision(offroadcar);
        offroadcar.addCollisionListener(offroadcarCollision);

        super.destroy();

        GameLevel world = (GameLevel)this.getWorld();
        if (world.isComplete()){
            world.getGame().goToNextLevel();
        }

    }
}