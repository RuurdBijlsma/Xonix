package xonix.Model;

import java.awt.geom.Point2D;


/**
 * Interface for any moveable objects
 */
public interface IMovable {
    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    Point2D getLocation();

    void setLocation(Point2D.Float loc);
}
