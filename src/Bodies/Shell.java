package Bodies;

import city.cs.engine.*;

// Creates a class for the blast body
public class Shell extends DynamicBody {

    // Provides the hitbox for the blast body
    private static final Shape laserShape = new PolygonShape(
            -0.555f,-0.835f,
            -0.35f,-1.18f,
            0.2f,-1.245f,
            0.505f,-1.08f,
            0.56f,-0.61f,
            0.045f,1.27f,
            -0.145f,1.21f,
            -0.55f,-0.555f);

    // Sets the blast body image to the image of the blast png
    private static final BodyImage image = new BodyImage("data/shell.png", 2.5f);

    // A constructor for the blast to add the image
    public Shell(World w) {
        super(w, laserShape);
        addImage(image);
    }

    public void addCollisionListener() {
    }
}