package game;

import Bodies.Driver;
import Bodies.Laser;
import Collisions.LaserCollision;
import Levels.GameLevel;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

// Creates a class for the driver controller that takes user inputs through the key listener
public class DriverController implements KeyListener {

    // Sets the walking_speed to 15
    private static final float WALKING_SPEED = 15;
    // Creates a private variable for driver
    private Driver driver;

    private Game game;

    // Creates a private static variable for score set to 0
    private static int score = 0;

    // Creates a static variable for laserSound which holds a SoundClip
    private static SoundClip laserSound;
    static {
        try {
            laserSound = new SoundClip("data/Laser Sound Effect.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    // A constructor to set the private variable driver to d
    public DriverController(Driver d) {
        driver = d;
    }

    // A getter for score to return score
    public static int getScore() { return score; }

    // A setter for score that increments score by 1
    public static void setScore() {
        score++;
        //System.out.println("Score: " + score);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // if statements to move the driver object using a keypress
        if (code == KeyEvent.VK_A) {
            driver.startWalking(-WALKING_SPEED);
        } else if (code == KeyEvent.VK_D) {
            driver.startWalking(WALKING_SPEED);
        } else if (code == KeyEvent.VK_W) {
            driver.setLinearVelocity(new Vec2(0, 10));
        } else if (code == KeyEvent.VK_S) {
            driver.setLinearVelocity(new Vec2(0, -10));
        // if statement to shoot the laser with a positive y velocity
        // lasers spawn from in front of the driver
        // laserSound is called when space bar is pressed
        } else if (code == KeyEvent.VK_SPACE) {
            laserSound.stop();
            Vec2 selfPoint = driver.getPosition().add(new Vec2(0, 2));
            Laser laser = new Laser(driver.getWorld());
            laser.setPosition(selfPoint);
            laser.setLinearVelocity(new Vec2(0, 50));
            LaserCollision laserCollision = new LaserCollision(laser);
            laser.addCollisionListener(laserCollision);
            laserSound.play();
        } else if (code == KeyEvent.VK_1) {
            try {
                GameSaverLoader.save(game.getLevel(), "data/save.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (code == KeyEvent.VK_3) {
            try {
                GameSaverLoader.load(game.getLevel().getGame(), "data/save.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // if statements to stop movement and give driver 0 velocity when keys are released
        if (code == KeyEvent.VK_A) {
            this.driver.stopWalking();
            this.driver.setLinearVelocity(new Vec2(0, 0));
        } else if (code == KeyEvent.VK_D) {
            this.driver.stopWalking();
            this.driver.setLinearVelocity(new Vec2(0, 0));
        } else if (code == KeyEvent.VK_W) {
            this.driver.setLinearVelocity(new Vec2(0, 0));
        } else if (code == KeyEvent.VK_S) {
            this.driver.setLinearVelocity(new Vec2(0, 0));
        }
    }

    public void updateDriver(Driver driver){
        this.driver = driver;
    }

}
