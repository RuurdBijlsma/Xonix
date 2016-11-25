package xonix;

class TimeTicket {

    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    private float width;
    private float height;
    private int seconds;

    TimeTicket(final java.awt.geom.Point2D.Float loc, final java.awt.Color color, int seconds, final float width, final float height) {
        setLocation(loc);
        setColor(color);
        setWidth(width);
        setHeight(height);
        setSeconds(seconds);
    }

    java.awt.geom.Point2D.Float getLocation() {
        return loc;
    }

    private void setLocation(java.awt.geom.Point2D.Float loc) {
        this.loc = loc;
    }

    java.awt.Color getColor() {
        return color;
    }

    private void setColor(final java.awt.Color color) {
        this.color = color;
    }

    float getWidth() {
        return width;
    }

    private void setWidth(final float width) {
        this.width = width;
    }

    float getHeight() {
        return height;
    }

    private void setHeight(final float height) {
        this.height = height;
    }

    boolean contains(final java.awt.geom.Point2D.Float that) {
        java.awt.geom.Point2D.Float thisLoc = this.getLocation();
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
