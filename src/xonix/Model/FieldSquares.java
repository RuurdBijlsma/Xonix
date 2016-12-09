package xonix.Model;

import xonix.Commands.AddSquareGroup;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FieldSquares implements Iterable<FieldSquare> {
    private static FieldSquares instance = null;
    public final Color[][] colors;
    private final FieldSquare[][] fieldSquares;
    private int iteratorColumnIndex = 0;
    private int iteratorRowIndex = 0;


    private FieldSquares() {
        fieldSquares = new FieldSquare[GameWorld.SQUARE_LENGTH][GameWorld.SQUARE_LENGTH];
        setFields(GameWorld.PLAYER_COLOR, GameWorld.SQUARE_COLOR);
        colors = new Color[GameWorld.SQUARE_LENGTH][GameWorld.SQUARE_LENGTH];
    }

    public static FieldSquares getInstance() {
        if (instance == null) {
            instance = new FieldSquares();
        }
        return instance;
    }

    public void drawLine(FieldSquare from, FieldSquare to, Color color) {
        Point2D.Float fromLoc = getCoordinates(from);
        Point2D.Float toLoc = getCoordinates(to);


        java.util.List<Integer> range;
        if (fromLoc.x == toLoc.x) {
            //vertical line
            int fromY = (int) fromLoc.y,
                    toY = (int) toLoc.y,
                    t = toY,
                    f = fromY;
            if (fromY > toY) {
                f = toY;
                t = fromY;
            }
            range = IntStream.rangeClosed(f, t).boxed().collect(Collectors.toList());
            int x = (int) fromLoc.x;
            for (int y : range) {
                if (fieldSquares[x][y].getColor() == GameWorld.SQUARE_COLOR) {
                    fieldSquares[x][y].setColor(color);
                }
            }
        } else {
            //horizontal line
            int fromX = (int) fromLoc.x,
                    toX = (int) toLoc.x,
                    t = toX,
                    f = fromX;
            if (fromX > toX) {
                f = toX;
                t = fromX;
            }
            range = IntStream.rangeClosed(f, t).boxed().collect(Collectors.toList());
            int y = (int) fromLoc.y;
            for (int x : range) {
                if (fieldSquares[x][y].getColor() == GameWorld.SQUARE_COLOR) {
                    fieldSquares[x][y].setColor(color);
                }
            }
        }
    }

    private Point2D.Float getCoordinates(FieldSquare square) {
        float x = square.getLocation().x;
        float y = square.getLocation().y;
        x /= square.getSize();
        y /= square.getSize();
        return new Point2D.Float((int) x, (int) y);
    }

    /**
     * Copies all fieldSquare colors to colors array
     */
    public void copyColors() {
        for (int x = 0; x < GameWorld.SQUARE_LENGTH; x++)
            for (int y = 0; y < GameWorld.SQUARE_LENGTH; y++)
                colors[x][y] = elementAt(x, y).getColor();
    }

    /**
     * Select a FieldSquare at a given position
     *
     * @param x X position of requested element
     * @param y Y position of requested element
     * @return FieldSquare at given position
     */
    public FieldSquare elementAt(int x, int y) {
        return fieldSquares[x][y];
    }

    /**
     * Resets fieldSquares
     */
    void reset() {
        setFields(GameWorld.PLAYER_COLOR, GameWorld.SQUARE_COLOR);
    }

    /**
     * @param edge  Color of the walls
     * @param inner Color of the playing field
     */
    private void setFields(Color edge, Color inner) {
        for (int y = 0; y < GameWorld.SQUARE_LENGTH; y++)
            for (int x = 0; x < GameWorld.SQUARE_LENGTH; x++)
                if (x == 0 || y == 0 || x == GameWorld.SQUARE_LENGTH - 1 || y == GameWorld.SQUARE_LENGTH - 1)
                    fieldSquares[x][y] = new FieldSquare(new Point2D.Float(x * GameWorld.SQUARE_UNITS, y * GameWorld.SQUARE_UNITS), edge, GameWorld.SQUARE_UNITS);
                else
                    fieldSquares[x][y] = new FieldSquare(new Point2D.Float(x * GameWorld.SQUARE_UNITS, y * GameWorld.SQUARE_UNITS), inner, GameWorld.SQUARE_UNITS);
    }

    /**
     * Changes color of the area that has been selected by the player
     *
     * @return Amount of squares that have been converted to cyan
     */
    int fillSquares() {
        AddSquareGroup addSquareGroup = new AddSquareGroup();
        addSquareGroup.actionPerformed(new ActionEvent(this, 0, "AddSquareGroup"));
        return addSquareGroup.filledSize;
    }

    @Override
    public Iterator<FieldSquare> iterator() {
        return new Iterator<FieldSquare>() {
            @Override
            public boolean hasNext() {
                boolean hasNextColumn = iteratorColumnIndex + 1 < GameWorld.SQUARE_LENGTH,
                        hasNextRow = iteratorRowIndex + 1 < GameWorld.SQUARE_LENGTH;
                if (!hasNextColumn && !hasNextRow) {
                    iteratorColumnIndex = 0;
                    iteratorRowIndex = 0;
                    return false;
                }
                return true;
            }

            @Override
            public FieldSquare next() {
                if (++iteratorColumnIndex >= GameWorld.SQUARE_LENGTH) {
                    iteratorColumnIndex = 0;
                    iteratorRowIndex++;
                }
                return fieldSquares[iteratorColumnIndex][iteratorRowIndex];
            }
        };
    }
}
