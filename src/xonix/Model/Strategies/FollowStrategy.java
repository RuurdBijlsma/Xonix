package xonix.Model.Strategies;

import xonix.Application;
import xonix.Model.GameWorld;
import xonix.Model.MonsterBall;

import java.awt.*;
import java.awt.geom.Point2D;

public class FollowStrategy extends MonsterStrategy {
    private static FollowStrategy instance = null;

    private FollowStrategy() {}

    public static FollowStrategy getInstance() {
        if (instance == null) {
            instance = new FollowStrategy();
        }
        return instance;
    }

    @Override
    public int getRadius() {
        return GameWorld.SMART_MONSTER_RADIUS;
    }

    @Override
    public Color getColor() {
        return GameWorld.SMART_MONSTER_COLOR;
    }

    @Override
    public int getMaxSpeed(){
        return GameWorld.SMART_MONSTER_MAXSPEED;
    }

    @Override
    public void initialize(MonsterBall monsterBall) {
        super.initialize(monsterBall);
        monsterBall.setSpeed(random.nextInt(getMaxSpeed()));
    }

    @Override
    public void changeLocation(MonsterBall monsterBall) {
        Point2D.Float carLocation = Application.controller.model.car.getLocation(),
                locationDelta = new Point2D.Float(
                        carLocation.x - monsterBall.getLocation().x,
                        carLocation.y - monsterBall.getLocation().y
                );
        double heading = Math.atan2(locationDelta.y, locationDelta.x);
        int head = 360 - (int) Math.toDegrees(heading);
        monsterBall.setHeading(head);
    }
}
