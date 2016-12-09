package xonix.Model;

import java.awt.*;
import java.awt.geom.Point2D;

public class FieldSquare extends BaseObject {
    private float size;

    FieldSquare(final Point2D.Float loc, final Color color, float size) {
        setLocation(loc);
        setColor(color);
        setSize(size);
    }

    public float getSize() {
        return size;
    }

    private void setSize(float size) {
        this.size = size;
    }

    /**
     * @return String containing fieldSquare information
     */
    @Override
    public String toString() {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " size=" + size;
    }
}
