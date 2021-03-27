package game;

import Bodies.Blast;
import Bodies.TurretCar;
import Collisions.BlastCollision;
import Levels.GameLevel;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

import java.util.Date;

public class TurretCarHandler implements StepListener {

    private GameLevel level;

    double previousTime = new Date().getTime() / 1000.;

    public TurretCarHandler(GameLevel level) { this.level = level; }

    @Override
    public void preStep(StepEvent stepEvent) {

        TurretCar turretCar = level.getTurretCar();

        if(turretCar == null){
            return;
        }

        double currentTime = new Date().getTime() / 1000.;
        if (currentTime - previousTime > 1f) {
            Vec2 selfPoint = turretCar.getPosition().add(new Vec2(0, -6));
            Blast blast = new Blast(turretCar.getWorld());
            blast.setPosition(selfPoint);
            blast.setLinearVelocity(new Vec2(0, -20));
            BlastCollision blastCollision = new BlastCollision(blast);
            blast.addCollisionListener(blastCollision);
            previousTime = currentTime;
        }

    }
    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
