package xonix;

import java.awt.Color;
import java.awt.geom.Point2D;

class FieldSquare extends BaseObject{
    private float size;

    FieldSquare(final Point2D.Float loc, final Color color, float size) {
        setLocation(loc);
        setColor(color);
        setSize(size);
    }

    float getSize() {
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
