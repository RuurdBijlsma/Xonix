package xonix;

import java.awt.*;
import java.awt.geom.Point2D;

class MonsterBall extends GameObject{
    private float radius;

    /**
     * @param loc Location of the monsterball
     * @param color Color of the monsterball
     * @param heading Heading of the monsterball (degrees)
     * @param speed Speed of the monsterball
     * @param radius Radius of the monsterball
     */
    MonsterBall(final Point2D.Float loc, final Color color, final int heading, final float speed, final float radius) {
        setLocation(loc);
        setColor(color);
        setHeading(heading);
        setSpeed(speed);
        setRadius(radius);
    }

    float getRadius() {
        return radius;
    }

    private void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * @param fieldSquares The object which contains all field squares
     * @param prevPos      Previous position
     * @param nextPos      Next position
     * @param state        Game state
     * @return True if collision with player line happens
     */
    @Override
    boolean checkCollisions(FieldSquares fieldSquares, Point2D.Float prevPos,  Point2D.Float nextPos, State state){
        FieldSquare prevSquare = GameWorld.getSquareAtPosition(fieldSquares, prevPos);
        FieldSquare nextSquare = GameWorld.getSquareAtPosition(fieldSquares, nextPos);

        if (prevSquare.getColor() == GameWorld.LINE_COLOR || nextSquare.getColor() == GameWorld.LINE_COLOR)
            return true; //collision with player line

        if (prevSquare.getColor() != nextSquare.getColor()) {
            //wall collision
            if (prevSquare.getColor() != GameWorld.getSquareAtPosition(fieldSquares, nextPos.x, prevPos.y).getColor()) {
                if (getHeading() < 180)
                    setHeading(180 - getHeading());
                else
                    setHeading(540 - getHeading());
            }
            if (prevSquare.getColor() != GameWorld.getSquareAtPosition(fieldSquares, prevPos.x, nextPos.y).getColor())
                setHeading(360 - getHeading());
        }

        return false;
    }

    /**
     * @return String containing monsterball information
     */
    @Override
    public String toString() {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " heading=" + heading + " speed=" + speed + " radius=" + radius;
    }
}
