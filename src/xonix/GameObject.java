package xonix;

import java.awt.geom.Point2D;

/**
 * Abstract class to extend for moving objects
 */
public abstract class GameObject extends BaseObject {
    /**
     * Calculates an objects next position based on heading, speed and location
     *
     * @param delta Time since last update
     * @return Next location for the gameobject to move to
     */
    private Point2D.Float nextLocation(float delta) {
        double radians = Math.toRadians(getHeading());
        float newx = (float) (getLocation().getX() + delta * getSpeed() * (float) Math.cos(radians)),
                newy = (float) (getLocation().getY() - delta * getSpeed() * (float) Math.sin(radians));
        if (newx < 0)
            newx = 0;
        else if (newx > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newx = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        if (newy < 0)
            newy = 0;
        else if (newy > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newy = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        return new Point2D.Float(newx, newy);
    }

    /**
     * @param fieldSquares The object which contains all field squares
     * @param delta        Time since last update
     * @param state        Game state
     * @return True if collision is detected
     */
    public boolean changeLocation(FieldSquares fieldSquares, float delta, State state) {
        Point2D.Float prevPos = getLocation();
        Point2D.Float nextPos = nextLocation(delta);

        if (checkCollisions(fieldSquares, prevPos, nextPos, state))
            return true;

        getLocation().setLocation(nextLocation(delta));
        return false;
    }

    /**
     * Method that should be implemented in class that extends this
     *
     * @param fieldSquares The object which contains all field squares
     * @param prevPos      Previous position
     * @param nextPos      Next position
     * @param state        Game state
     * @return True if collision is detected
     */
    abstract boolean checkCollisions(FieldSquares fieldSquares, Point2D.Float prevPos, Point2D.Float nextPos, State state);
}
