package xonix;

import java.awt.Color;
import java.awt.geom.Point2D;

class Car {
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

    Point2D.Float getLocation() {
        return loc;
    }

    private void setLocation(Point2D.Float loc) {
        this.loc = loc;
    }

    Color getColor() {
        return color;
    }

    private void setColor(final Color color) {
        this.color = color;
    }

    private int getHeading() {
        return heading;
    }

    final void setHeading(final int heading) {
        this.heading = heading;
    }

    float getSpeed() {
        return speed;
    }

    final void setSpeed(final float speed) {
        this.speed = speed;
    }

    int getWidth() {
        return width;
    }

    private void setWidth(final int width) {
        this.width = width;
    }

    int getHeight() {
        return height;
    }

    private void setHeight(final int height) {
        this.height = height;
    }

    private Point2D.Float nextLocation(float delta) {
        double radians = Math.toRadians(getHeading());
        float newx = getLocation().x + delta * getSpeed() * (float) Math.cos(radians);
        if (newx < 0)
            newx = 0;
        else if (newx > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newx = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        float newy = getLocation().y - delta * getSpeed() * (float) Math.sin(radians);
        if (newy < 0)
            newy = 0;
        else if (newy > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newy = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        return new Point2D.Float(newx, newy);
    }

    void changeLocation(FieldSquares fss, State state, float delta) {
        Point2D.Float prev = getLocation();
        Point2D.Float next = nextLocation(delta);
        FieldSquare fsprev = fss.elementAt((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5));
        FieldSquare fsnext = fss.elementAt((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5));
        if (fsnext.getColor() == GameWorld.SQUARE_COLOR)
            fsnext.setColor(GameWorld.LINE_COLOR);
        else if (fsnext.getColor() == GameWorld.PLAYER_COLOR && fsprev.getColor() == GameWorld.LINE_COLOR)
            state.addcscore(fss.fillSquares());
        getLocation().setLocation(next);
    }

    @Override
    public String toString() {
        return "loc=" + getLocation().x + "," + getLocation().y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height;
    }
}
