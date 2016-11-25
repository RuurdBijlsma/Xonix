package xonix;

import java.awt.geom.Point2D;

/**
 * Created by Ruurd on 25-11-2016.
 */
abstract class Movable implements IMovable{
    Point2D.Float nextLocation(float delta) {
        double radians = Math.toRadians(getHeading());
        float newx = (float) (getLocation().getX() + delta * getSpeed() * (float) Math.cos(radians));
        if (newx < 0)
            newx = 0;
        else if (newx > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newx = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        float newy = (float) (getLocation().getY() - delta * getSpeed() * (float) Math.sin(radians));
        if (newy < 0)
            newy = 0;
        else if (newy > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newy = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        return new Point2D.Float(newx, newy);
    }

    abstract boolean changeLocation(FieldSquares fss, float delta, State state);
}
