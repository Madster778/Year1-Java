package Bodies;

import city.cs.engine.*;

// Creates a class for the laser body
public class Laser extends DynamicBody {

    // Provides the hitbox for the laser body
    private static final Shape laserShape = new PolygonShape(
            0.029f, 0.422f,
            0.083f, 0.324f,
            0.105f, -0.312f,
            0.053f, -0.42f,
            -0.039f, -0.424f,
            -0.105f, -0.366f,
            -0.125f, 0.364f,
            -0.077f, 0.414f);

    // Sets the laser body image to the image of the laser png
    private static final BodyImage image = new BodyImage("data/laser.png", 1);

    // A constructor for the lasers to add the image and set it to negative gravity
    public Laser(World w) {
        super(w, laserShape);
        setGravityScale(-9.8f);
        addImage(image);
    }

    public void addCollisionListener() {
    }
}
