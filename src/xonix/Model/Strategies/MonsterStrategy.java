package xonix.Model.Strategies;

import xonix.Model.GameWorld;
import xonix.Model.MonsterBall;

import java.awt.*;
import java.util.Random;

public abstract class MonsterStrategy {
    protected Random random = new Random();

    public int getRadius() {
        return GameWorld.MONSTER_RADIUS;
    }

    public Color getColor() {
        return GameWorld.MONSTER_COLOR;
    }

    public int getMaxSpeed() {
        return GameWorld.MONSTER_MAXSPEED;
    }

    public void initialize(MonsterBall monsterBall) {
        monsterBall.setSpeed(random.nextInt(getMaxSpeed()));
        monsterBall.setColor(getColor());
        monsterBall.setRadius(getRadius());
    }

    public void changeLocation(MonsterBall monsterBall) {
    }
}
