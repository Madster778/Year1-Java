package Bodies;

import Collisions.BarricadeCollision;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;

// Creates a class for the barricade body
public class Barricade extends DynamicBody {

    // Creates an array for with the x locations for each lane
    private float xPositions[] = new float[] {
            -10.1f, -4.8f, 0.3f, 5.3f, 10.2f,
    };

    // A getter to return the random location for one of the roads
    public float getRoadPosition() {
        return xPositions[random.nextInt(xPositions.length)];
    }

    private Random random = new Random();

    // Provides the hitbox for the barricade body
    private static final Shape barricadeShape = new PolygonShape(
            -1.832f,0.923f,
            1.847f,0.915f,
            1.847f,-0.909f,
            -1.832f,-0.917f);

    // Sets the barricade body image to the image of the barricade png
    private static final BodyImage image =
            new BodyImage("data/barricade.png", 2f);

    // A constructor for barricade to add the image
    public Barricade(World w) {
        super(w,barricadeShape);
        addImage(image);
        float x = getRoadPosition();
        setPosition(new Vec2(x, 30));
        setLinearVelocity(new Vec2(0, -10));
        BarricadeCollision barricadeDestroy = new BarricadeCollision(this);
        this.addCollisionListener(barricadeDestroy);
    }

}