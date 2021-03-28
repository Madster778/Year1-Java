package Collisions;

import Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import Bodies.FireTruck;
import game.Game;

// Creates a class for the firetruck collisions
public class FireTruckCollision implements CollisionListener {

    // Creates a private variable for firetruck
    private FireTruck firetruck;

    // A constructor to set the private variable firetruck to f
    public FireTruckCollision(FireTruck f) {
        this.firetruck = f;
    }

    public FireTruckCollision(GameLevel level, Game game) {
        this.firetruck = level.getFireTruck();
    }

    // A constructor to have the firetrucks collide with static bodies
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof FireTruck && e.getOtherBody() instanceof StaticBody) {
            firetruck.destroy();
        }
    }
}