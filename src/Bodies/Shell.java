package Bodies;

import city.cs.engine.*;

// Creates a class for the shell body
public class Shell extends DynamicBody {

    // Provides the hitbox for the shell body
    private static final Shape laserShape = new PolygonShape(
            -0.31f,1.07f,
            -0.275f,-0.505f,
            0.005f,-1.28f,
            0.275f,-0.505f,
            0.305f,1.06f,
            0.24f,1.245f,
            -0.24f,1.24f);

    // Sets the blast body image to the image of the shell png
    private static final BodyImage image = new BodyImage("data/shell.png", 2.5f);

    // A constructor for the shell to add the image
    public Shell(World w) {
        super(w, laserShape);
        addImage(image);
    }

    public void addCollisionListener() {
    }
}