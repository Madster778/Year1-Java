package Collisions;

import Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import game.Game;
import Bodies.PoliceCar;

// Creates a class for the police car collisions
public class PoliceCarCollision implements CollisionListener {

    // Creates a private variable for police car
    private PoliceCar policecar;

    // A constructor to set the private variable policecar to p
    public PoliceCarCollision(PoliceCar p) {
        this.policecar = p;
    }

    public PoliceCarCollision(GameLevel level, Game game) {
        this.policecar = level.getPoliceCar();
    }

    // A constructor to have the police cars collide with static bodies
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof PoliceCar && e.getOtherBody() instanceof StaticBody) {
            policecar.destroy();
        }
    }
}