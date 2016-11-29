package xonix;

import java.awt.geom.Point2D;


/**
 * Interface for any moveable objects
 */
public interface IMovable {
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setLocation(Point2D.Float loc);
    Point2D getLocation();
}
