package xonix;

import java.awt.Color;
import java.awt.geom.Point2D;

class TimeTicket implements IColorable, IMovable{

    private Point2D.Float loc;
    private Color color;
    private int width;
    private int height;
    private int seconds;

    TimeTicket(final Point2D.Float loc, final Color color, int seconds, final int width, final int height) {
        setLocation(loc);
        setColor(color);
        setWidth(width);
        setHeight(height);
        setSeconds(seconds);
    }

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

    boolean contains(final Point2D.Float that) {
        Point2D.Float thisLoc = this.getLocation();
        return that.x >= thisLoc.x && that.x <= thisLoc.x + this.getWidth() && that.y >= thisLoc.y && that.y <= thisLoc.y + this.getHeight();
    }

    int getSeconds() {
        return seconds;
    }

    private void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " width=" + width + " height=" + height + " seconds=" + seconds;
    }
}
