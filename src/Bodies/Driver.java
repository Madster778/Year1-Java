package Bodies;

import city.cs.engine.*;

// Creates a class for the player controlled car
public class Driver extends Walker {

    // Provides the hitbox for the Driver body
    private static final Shape driverShape = new PolygonShape(
            -1.05f, -2.95f,
            1.05f, -2.95f,
            1.32f, -1.82f,
            1.34f, 2.24f,
            0.73f, 2.99f,
            -0.73f, 2.99f,
            -1.32f, 2.24f,
            -1.34f, -1.82f);

    // Sets the driver body image to the image of the driver png
    private static final BodyImage image = new BodyImage("data/driver.png", 6f);

    // Creates a private variable for lives
    private static int lives;
    // Creates a private variable for kills
    private static int kills;

    // A constructor for the driver to add the image and number of lives
    public Driver(World world) {
        super(world, driverShape);
        addImage(image);
        lives = 10;
        kills = 0;
    }

    // A getter for kills, to return kills
    public static int getKills() { return kills; }

    // A setter for kills that increments kills by 1
    public static void setKills() {
        kills++;
        System.out.println("Kills: " + kills);
    }

    // A method used to decrease the lives and stop the game when lives run out
    public void decreaseLives() {
        lives--;
        if (lives < 1) {
            System.out.println("You got hit: " + "Lives left = " + lives);
            System.out.println("GAME OVER!");
            getWorld().stop();
        } else {
            System.out.println("You got hit: " + "Lives left = " + lives);
        }
    }

    // A getter for lives to return lives
    public static int getLives() { return lives; }

    public static void setLives(int lives) {
        Driver.lives = lives;
    }

    public void setKills(int kills) { Driver.kills = kills; }
}
