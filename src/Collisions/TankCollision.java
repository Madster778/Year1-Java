package Collisions;

import Bodies.Blast;
import Bodies.Driver;
import Bodies.Tank;
import Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import game.Game;
import Bodies.TurretCar;

// Creates a class for the firetruck collisions
public class TankCollision implements CollisionListener {

    // Creates a private variable for tank
    private Tank tank;

    // A constructor to set the private variable tank to t
    public TankCollision(Tank t) { this.tank = t; }

    public TankCollision(GameLevel level, Game game) { this.tank = level.getTank(); }

    // A constructor to have the tanks collide with static bodies
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Blast) {
            e.getOtherBody().destroy();
        }
    }
}