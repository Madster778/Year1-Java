package Collisions;

import Bodies.*;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

// Creates a class for the barricade collisions
public class BlastCollision implements CollisionListener {

    // Creates a private variable for barricade
    private Blast blast;
    // A constructor to set the private variable barricade to ba
    public BlastCollision(Blast bl){ this.blast = bl; }

    // A constructor to have the blast collide and decrease the driver health
    // A collision for static bodies, to destroy blasts when they touch a wall
    // if a blast collides with a laser, both are destroyed
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Driver) {
            ((Driver) e.getOtherBody()).decreaseLives();
            blast.destroy();
        } else if (e.getOtherBody() instanceof TurretCar) {
            blast.destroy();
        } else if (e.getOtherBody() instanceof StaticBody) {
            blast.destroy();
        }else if (e.getOtherBody() instanceof Laser){
            blast.destroy();
            e.getOtherBody().destroy();
        }
    }
}