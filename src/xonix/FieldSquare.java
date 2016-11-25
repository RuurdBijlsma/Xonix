package xonix;

import java.awt.Color;
import java.awt.geom.Point2D;

class FieldSquare {

    private Point2D.Float loc;
    private Color color;
    private float size;

    FieldSquare(final Point2D.Float loc, final Color color, float size) {
        setLocation(loc);
        setColor(color);
        setSize(size);
    }

    Point2D.Float getLocation() {
        return loc;
    }

    private void setLocation(Point2D.Float loc) {
        this.loc = loc;
    }

    Color getColor() {
        return color;
    }

    void setColor(final Color color) {
        this.color = color;
    }

    float getSize() {
        return size;
    }

    private void setSize(float size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " size=" + size;
    }
}
