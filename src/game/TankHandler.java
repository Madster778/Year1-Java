package game;

import Bodies.Shell;
import Bodies.Tank;
import Collisions.ShellCollision;
import Levels.GameLevel;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

import java.util.Date;


public class TankHandler implements StepListener {

    private GameLevel level;

    double previousTime = new Date().getTime() / 1000.;

    public TankHandler(GameLevel level) { this.level = level; }

    @Override
    public void preStep(StepEvent stepEvent) {

        Tank tank = level.getTank();

        if(tank == null){
            return;
        }

        double currentTime = new Date().getTime() / 1000.;
        if (currentTime - previousTime > 1f) {
            Vec2 selfPoint = tank.getPosition().add(new Vec2(0, -7));
            Shell shell = new Shell(tank.getWorld());
            shell.setPosition(selfPoint);
            shell.setLinearVelocity(new Vec2(0, -20));
            ShellCollision shellCollision = new ShellCollision(shell);
            shell.addCollisionListener(shellCollision);
            previousTime = currentTime;
        }

    }
    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
