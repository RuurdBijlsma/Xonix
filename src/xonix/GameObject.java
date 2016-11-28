package xonix;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Ruurd on 25-11-2016.
 */
abstract class GameObject implements IMovable, ISteerable, IColorable {
    Point2D.Float loc;
    Color color;
    int heading;
    float speed;
    int width;
    int height;

    Point2D.Float nextLocation(float delta) {
        double radians = Math.toRadians(getHeading());
        float newx = (float) (getLocation().getX() + delta * getSpeed() * (float) Math.cos(radians)),
                newy = (float) (getLocation().getY() - delta * getSpeed() * (float) Math.sin(radians));
        if (newx < 0)
            newx = 0;
        else if (newx > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newx = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        if (newy < 0)
            newy = 0;
        else if (newy > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newy = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        return new Point2D.Float(newx, newy);
    }

    boolean changeLocation(FieldSquares fieldSquares, float delta, State state) {
        Point2D.Float prevPos = getLocation();
        Point2D.Float nextPos = nextLocation(delta);

        if (checkCollisions(fieldSquares, prevPos, nextPos, state))
            return true;

        getLocation().setLocation(nextLocation(delta));
        return false;
    }

    abstract boolean checkCollisions(FieldSquares fieldSquares, Point2D.Float prevPos, Point2D.Float nextPos, State state);


    @Override
    public Point2D.Float getLocation() {
        return loc;
    }

    public void setLocation(Point2D.Float loc) {
        this.loc = loc;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(final int heading) {
        this.heading = heading;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }
}
