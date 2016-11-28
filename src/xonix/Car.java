package xonix;

import java.awt.*;
import java.awt.geom.Point2D;

class Car extends GameObject{

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
    boolean checkCollisions(FieldSquares fieldSquares, Point2D.Float prevPos,  Point2D.Float nextPos, State state){
        FieldSquare prevSquare = GameWorld.getSquareAtPosition(fieldSquares, prevPos);
        FieldSquare nextSquare = GameWorld.getSquareAtPosition(fieldSquares, nextPos);
        if (nextSquare.getColor() == GameWorld.SQUARE_COLOR)
            nextSquare.setColor(GameWorld.LINE_COLOR);

        else if (nextSquare.getColor() == GameWorld.PLAYER_COLOR && prevSquare.getColor() == GameWorld.LINE_COLOR)
            state.addcscore(fieldSquares.fillSquares());

        return false;
    }

    @Override
    public String toString() {
        return "loc=" + getLocation().x + "," + getLocation().y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height;
    }
}
