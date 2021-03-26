package Collisions;

import Levels.GameLevel;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import game.Game;
import Bodies.TurretCar;

// Creates a class for the firetruck collisions
public class TurretCarCollision implements CollisionListener {

    // Creates a private variable for firetruck
    private TurretCar turretCar;

    // A constructor to set the private variable tank to t
    public TurretCarCollision(TurretCar tc) { this.turretCar = tc; }

    public TurretCarCollision(GameLevel level, Game game) { this.turretCar = level.getTurretCar(); }

    // A constructor to have the tanks collide with static bodies
    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof TurretCar && e.getOtherBody() instanceof StaticBody) {
            turretCar.destroy();
        }
    }
}