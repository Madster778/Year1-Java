package Bodies;

import Collisions.TankCollision;
import Levels.GameLevel;
import city.cs.engine.*;
import game.DriverController;
import game.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Date;

// Creates a class for the tank body
public class Tank extends DynamicBody implements StepListener {

    private enum State{
        LEFT_MOVEMENT, RIGHT_MOVEMENT, STANDING_STILL
    }

    private Game game;

    private Tank tank;

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
            -3.97f,-6.44f,
            3.88f,-6.52f,
            3.88f,8.71f,
            -4.04f,8.71f);

    // Sets the tank body image to the image of the tank png
    private static final BodyImage image = new BodyImage("data/tank.png", 18f);

    private final GameLevel level;
    public static final float RANGE = 100;
    private State state;
    // Creates a private variable for health
    private static int health;

    /**
     * The tank is able to follow the driver when in range.
     * The tank is initially spawned without moving left or right.
     *
     * @param level the location of level, given the location from GameLevel.
     */
    // A constructor for tank to add the image and health
    public Tank(GameLevel level) {
        super(level, turretcarShape);
        this.level = level;
        addImage(image);
        state = State.STANDING_STILL;
        getWorld().addStepListener((StepListener) this);
        health = 100;
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

    public static int getHealth() { return health; }

    // A method used to allow the tanks to respawn when each of the tanks are destroyed
    @Override
    public void destroy() {
        Tank tank = new Tank(level);
        tank.setPosition(new Vec2(level.getRoadPosition(), 30));
        tank.setLinearVelocity(new Vec2(0, -1));
        TankCollision tankCollision = new TankCollision(tank);
        tank.addCollisionListener(tankCollision);
        super.destroy();
        GameLevel world = (GameLevel)this.getWorld();
        if (world.isComplete()){
            world.getGame().goToNextLevel();
        }
        Game.getLevel().setTank(tank);
    }

    public boolean inRangeUpLeft(){
        Body r = level.getDriver();
        float gap = getPosition().x - r.getPosition().x;
        return gap < RANGE && gap > 0;
    }

    public boolean inRangeUpRight(){
        Body r = level.getDriver();
        float gap = getPosition().x - r.getPosition().x;
        return gap > -RANGE && gap < 0;
    }

    public boolean inRangeRocket(){
        Body d = level.getDriver();
        return getPosition().x == d.getPosition().x;
    }

    public void preStep(StepEvent e){
        if (inRangeUpRight()) {
            if (state != State.RIGHT_MOVEMENT) {
                state = State.RIGHT_MOVEMENT;
            }
        }
        else if (inRangeUpLeft()) {
            if (state != State.LEFT_MOVEMENT)
                state = State.LEFT_MOVEMENT;
        }
        else if(inRangeRocket()) {
            if(state != State.STANDING_STILL) {
                state = State.STANDING_STILL;
            }
        }
        else {
            if (state != State.STANDING_STILL) {
                state = State.STANDING_STILL;
            }
        }
        refreshMovement();
    }

    private void refreshMovement() {
        switch (state) {
            case LEFT_MOVEMENT:
                setLinearVelocity(new Vec2(-5, -1f));
                break;
            case RIGHT_MOVEMENT:
                setLinearVelocity(new Vec2(5, -1f));
                break;
            case STANDING_STILL:
                setLinearVelocity(new Vec2(0, -1f));
            default:
        }
    }

    public void postStep(StepEvent e){

    }

}