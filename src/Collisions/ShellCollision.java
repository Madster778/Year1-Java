package Collisions;

import Bodies.*;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

// Creates a class for the barricade collisions
public class ShellCollision implements CollisionListener {

    // Creates a private variable for barricade
    private Shell shell;
    // A constructor to set the private variable barricade to ba
    public ShellCollision(Shell s){ this.shell = s; }

    // A constructor to have the shell collide and decrease the driver health
    // A collision for static bodies, to destroy shells when they touch a wall
    // if a shell collides with a laser, both are destroyed
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Driver) {
            ((Driver) e.getOtherBody()).decreaseLives();
            shell.destroy();
        } else if (e.getOtherBody() instanceof TurretCar) {
            shell.destroy();
        } else if (e.getOtherBody() instanceof StaticBody) {
            shell.destroy();
        }else if (e.getOtherBody() instanceof Laser){
            shell.destroy();
            e.getOtherBody().destroy();
        }
    }
}