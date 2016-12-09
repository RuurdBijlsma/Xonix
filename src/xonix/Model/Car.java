package xonix.Model;

import java.awt.*;
import java.awt.geom.Point2D;

public class Car extends GameObject {

    /**
     * @param loc     Location of the car
     * @param color   Color of the car
     * @param heading Heading of the car (degrees)
     * @param speed   Speed of the car
     * @param width   Width of the car
     * @param height  Height of the car
     */
    Car(final Point2D.Float loc, final Color color, final int heading, final int speed, final int width, final int height) {
        setLocation(loc);
        setColor(color);
        setHeading(heading);
        setSpeed(speed);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Resets car location, heading, color, speed and size
     */
    void reset() {
        setLocation(new Point2D.Float(GameWorld.SQUARE_LENGTH / 2 * GameWorld.SQUARE_UNITS, (GameWorld.SQUARE_LENGTH - 1) * GameWorld.SQUARE_UNITS));
        setColor(GameWorld.CAR_COLOR);
        setHeading(270);
        setSpeed(50);
        setWidth(GameWorld.SQUARE_UNITS);
        setHeight(GameWorld.SQUARE_UNITS);
    }

    /**
     * Checks for collisions with the walls
     *
     * @param fieldSquares The object which contains all field squares
     * @param prevPos      Previous position of the car
     * @param nextPos      Next position of the car
     * @param state        Game state
     */
    @Override
    boolean checkCollisions(FieldSquares fieldSquares, Point2D.Float prevPos, Point2D.Float nextPos, RealState state) {
        FieldSquare prevSquare = GameWorld.getSquareAtPosition(fieldSquares, prevPos);
        FieldSquare nextSquare = GameWorld.getSquareAtPosition(fieldSquares, nextPos);
        if (nextSquare.getColor() == GameWorld.SQUARE_COLOR) {
            fieldSquares.drawLine(prevSquare, nextSquare, GameWorld.LINE_COLOR);
        }

        else if (nextSquare.getColor() == GameWorld.PLAYER_COLOR && prevSquare.getColor() == GameWorld.LINE_COLOR) {
            fieldSquares.drawLine(prevSquare, nextSquare, GameWorld.LINE_COLOR);
            state.addCurrentScore(fieldSquares.fillSquares());
        }

        return false;
    }

    /**
     * @return String containing car information
     */
    @Override
    public String toString() {
        return "loc=" + getLocation().x + "," + getLocation().y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height;
    }
}
