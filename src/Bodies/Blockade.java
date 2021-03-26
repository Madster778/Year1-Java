package Bodies;

import Collisions.BlockadeCollision;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;

// Creates a class for the blockade body
public class Blockade extends DynamicBody {

    // Creates an array for with the x locations for each lane
    private float xPositions[] = new float[] {
            -10.1f, -4.8f, 0.3f, 5.3f, 10.2f,
    };

    // A getter to return the random location for one of the roads
    public float getRoadPosition() {
        return xPositions[random.nextInt(xPositions.length)];
    }

    private Random random = new Random();

    // Provides the hitbox for the blockade body
    private static final Shape blockadeShape = new PolygonShape(
            -1.65f,7.03f,
            1.62f,7.0f,
            1.62f,-6.97f,
            -1.68f,-6.97f);

    // Sets the blockade body image to the image of the blockade png
    private static final BodyImage image =
            new BodyImage("data/blockade.png", 14f);

    // A constructor for blockade to add the image
    public Blockade(World w) {
        super(w,blockadeShape);
        addImage(image);
        float x = getRoadPosition();
        setPosition(new Vec2(x, 30));
        setLinearVelocity(new Vec2(0, -10));
        BlockadeCollision blockadeDestroy = new BlockadeCollision(this);
        this.addCollisionListener(blockadeDestroy);
    }

}
