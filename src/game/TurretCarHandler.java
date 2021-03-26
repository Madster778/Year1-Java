package game;

import Bodies.TurretCar;
import Bodies.Laser;
import Levels.GameLevel;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.util.Date;

public class TurretCarHandler extends World implements StepListener {

    private static Bodies.TurretCar TurretCar;

    public static void setTurretCar(Bodies.TurretCar turretCar) { TurretCar = turretCar; }

    double previousTime = new Date().getTime() / 1000.;

    public TurretCarHandler(TurretCar tc) { TurretCar = tc; }

    @Override
    public void preStep(StepEvent stepEvent) {
                double currentTime = new Date().getTime() / 1000.;
                if (currentTime - previousTime > 1.f) {
                    Vec2 selfPoint = TurretCar.getPosition().add(new Vec2(0, -20));
                    Laser laser = new Laser(TurretCar.getWorld());
                    laser.setPosition(selfPoint);
                    laser.setLinearVelocity(new Vec2(0, -20));
                    System.out.println(currentTime);
                    previousTime = currentTime;
                }
            }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
