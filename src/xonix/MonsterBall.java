package xonix;

import java.awt.*;
import java.awt.geom.Point2D;

class MonsterBall extends Movable {

    private Point2D.Float loc;
    private Color color;
    private int heading;
    private float speed;
    private float radius;

    MonsterBall(final Point2D.Float loc, final Color color, final int heading, final float speed, final float radius) {
        setLocation(loc);
        setColor(color);
        setHeading(heading);
        setSpeed(speed);
        this.setRadius(radius);
    }

    @Override
    public Point2D.Float getLocation() {
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


    public int getHeading() {
        return heading;
    }

    public void setHeading(final int heading) {
        this.heading = heading;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setLocation(double x, double y) {

    }

    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public void setHeight(int height) {

    }

    float getRadius() {
        return radius;
    }

    private void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    boolean changeLocation(FieldSquares fss, float delta, State state){
        Point2D.Float prev = getLocation();
        Point2D.Float next = nextLocation(delta);
        FieldSquare fsprev = fss.elementAt((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5));
        FieldSquare fsnext = fss.elementAt((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5));

        if (fsprev.getColor() == GameWorld.LINE_COLOR || fsnext.getColor() == GameWorld.LINE_COLOR)
            return true;

        if (fsprev.getColor() != fsnext.getColor()) {
            if (fss.elementAt((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor() != fss.elementAt((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor())
                if (getHeading() < 180)
                    setHeading(180 - getHeading());
                else
                    setHeading(540 - getHeading());
            if (fss.elementAt((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor() != fss.elementAt((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5)).getColor())
                setHeading(360 - getHeading());
        }
        getLocation().setLocation(nextLocation(delta));
        return false;
    }

    @Override
    public String toString() {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " heading=" + heading + " speed=" + speed + " radius=" + radius;
    }
}
