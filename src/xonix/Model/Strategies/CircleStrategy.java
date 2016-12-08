package xonix.Model.Strategies;

import xonix.Model.GameWorld;
import xonix.Model.MonsterBall;

import java.awt.*;

public class CircleStrategy extends MonsterStrategy {
    private static CircleStrategy instance = null;

    private CircleStrategy() {}

    public static CircleStrategy getInstance() {
        if (instance == null) {
            instance = new CircleStrategy();
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
    public void changeLocation(MonsterBall monsterBall) {
        monsterBall.setHeading(monsterBall.getHeading() + 5);
    }
}
