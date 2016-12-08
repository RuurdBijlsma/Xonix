package xonix;

import java.awt.*;
import java.awt.geom.Point2D;

public class SmartMonsterBall extends MonsterBall {

    /**
     * @param loc     Location of the monsterball
     * @param color   Color of the monsterball
     * @param heading Heading of the monsterball (degrees)
     * @param speed   Speed of the monsterball
     * @param radius  Radius of the monsterball
     */
    public SmartMonsterBall(Point2D.Float loc, Color color, int heading, float speed, float radius) {
        super(loc, color, heading, speed, radius);
    }

    /**
     * @param fieldSquares The object which contains all field squares
     * @param delta        Time since last update
     * @param state        Game state
     * @return wether a collision happened with the player line
     */
    @Override
    public boolean changeLocation(FieldSquares fieldSquares, float delta, State state) {
        Point2D.Float carLocation = Application.controller.model.car.getLocation(),
                locationDelta = new Point2D.Float(
                        carLocation.x - getLocation().x,
                        carLocation.y - getLocation().y
                );
        double heading = Math.atan2(locationDelta.y, locationDelta.x);
        int head = 360 - (int)Math.toDegrees(heading);
        setHeading(head);

        return super.changeLocation(fieldSquares, delta, state);
    }
}
