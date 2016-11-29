package xonix;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Abstract class to extend in order to get basic game functionality
 */
abstract class BaseObject implements IMovable, ISteerable, IColorable {
    Point2D.Float loc;
    Color color;
    int heading;
    float speed;
    int width;
    int height;

    /**
     * Checks if the given location is within the object bounds
     * @param that Location to check
     * @return Whether this object contains the given location
     */
    boolean contains(final Point2D.Float that) {
        Point2D.Float thisLoc = this.getLocation();
        return that.x >= thisLoc.x && that.x <= thisLoc.x + this.getWidth() && that.y >= thisLoc.y && that.y <= thisLoc.y + this.getHeight();
    }

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
