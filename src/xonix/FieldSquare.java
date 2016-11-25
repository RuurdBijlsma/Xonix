package xonix;

class FieldSquare {

    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    private float size;

    FieldSquare(final java.awt.geom.Point2D.Float loc, final java.awt.Color color, float size) {
        setLocation(loc);
        setColor(color);
        setSize(size);
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

    void setColor(final java.awt.Color color) {
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
