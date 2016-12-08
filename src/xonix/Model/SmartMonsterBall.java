package xonix.Model;

import xonix.Application;
import xonix.Model.Strategies.FollowStrategy;
import xonix.Model.Strategies.MonsterStrategy;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class SmartMonsterBall extends MonsterBall {

    /**
     * @param loc     Location of the monsterball
     * @param color   Color of the monsterball
     * @param heading Heading of the monsterball (degrees)
     * @param speed   Speed of the monsterball
     * @param radius  Radius of the monsterball
     */
    private MonsterStrategy strategy;

    public SmartMonsterBall(Point2D.Float loc, Color color, int heading, float speed, float radius, MonsterStrategy strat) {
        super(loc, color, heading, speed, radius);
        strategy = strat;
    }

    public MonsterStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MonsterStrategy strat) {
        strat.initialize(this);
        strategy = strat;
    }

    /**
     * @param fieldSquares The object which contains all field squares
     * @param delta        Time since last update
     * @param state        Game state
     * @return wether a collision happened with the player line
     */
    @Override
    public boolean changeLocation(FieldSquares fieldSquares, float delta, RealState state) {
        strategy.changeLocation(this);

        return super.changeLocation(fieldSquares, delta, state);
    }
}
