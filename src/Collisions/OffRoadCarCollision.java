package Collisions;

import Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import game.Game;
import Bodies.OffRoadCar;

// Creates a class for the off-road car collisions
public class OffRoadCarCollision implements CollisionListener {

    // Creates a private variable for off-road car
    private OffRoadCar offroadcar;

    // A constructor to set the private variable offroadcar to o
    public OffRoadCarCollision(OffRoadCar o) {
        this.offroadcar = o;
    }

    public OffRoadCarCollision(GameLevel level, Game game) {
        this.offroadcar = level.getOffRoadCar();
    }

    // A constructor to have the off-road cars collide with static bodies
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof OffRoadCar && e.getOtherBody() instanceof StaticBody) {
            offroadcar.destroy();
       }
    }
}