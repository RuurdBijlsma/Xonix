package xonix;

import java.awt.*;
import java.awt.geom.Point2D;

class Car extends Movable implements IColorable, ISteerable{
    private Point2D.Float loc;
    private Color color;
    private int heading;
    private float speed;
    private int width;
    private int height;

    Car(final Point2D.Float loc, final Color color, final int heading, final int speed, final int width, final int height) {
        setLocation(loc);
        setColor(color);
        setHeading(heading);
        setSpeed(speed);
        setWidth(width);
        setHeight(height);
    }

    void reset() {
        setLocation(new Point2D.Float(GameWorld.SQUARE_LENGTH / 2 * GameWorld.SQUARE_UNITS, (GameWorld.SQUARE_LENGTH - 1) * GameWorld.SQUARE_UNITS));
        setColor(GameWorld.CAR_COLOR);
        setHeading(270);
        setSpeed(50);
        setWidth(GameWorld.SQUARE_UNITS);
        setHeight(GameWorld.SQUARE_UNITS);
    }

    @Override
    public Point2D.Float getLocation() {
        return loc;
    }

    private void setLocation(Point2D.Float loc) {
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

    @Override
    public void setLocation(double x, double y) {

    }

    public void setHeight(final int height) {
        this.height = height;
    }

    @Override
    boolean changeLocation(FieldSquares fss, float delta, State state) {
        Point2D.Float prev = (Point2D.Float) getLocation();
        Point2D.Float next = nextLocation(delta);
        FieldSquare fsprev = fss.elementAt((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5));
        FieldSquare fsnext = fss.elementAt((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5));
        if (fsnext.getColor() == GameWorld.SQUARE_COLOR)
            fsnext.setColor(GameWorld.LINE_COLOR);
        else if (fsnext.getColor() == GameWorld.PLAYER_COLOR && fsprev.getColor() == GameWorld.LINE_COLOR)
            state.addcscore(fss.fillSquares());
        getLocation().setLocation(next);
        return false;
    }

    @Override
    public String toString() {
        return "loc=" + getLocation().x + "," + getLocation().y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height;
    }
}
