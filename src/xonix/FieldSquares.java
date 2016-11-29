package xonix;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Stack;

class FieldSquares {
    private static FieldSquares instance = null;

    public static FieldSquares getInstance() {
        if (instance == null) {
            instance = new FieldSquares();
        }
        return instance;
    }

    private final FieldSquare[][] fieldSquares;
    private final Color[][] colors;

    private FieldSquares() {
        fieldSquares = new FieldSquare[GameWorld.SQUARE_LENGTH][GameWorld.SQUARE_LENGTH];
        setFields(GameWorld.PLAYER_COLOR, GameWorld.SQUARE_COLOR);
        colors = new Color[GameWorld.SQUARE_LENGTH][GameWorld.SQUARE_LENGTH];
    }

    /**
     * Select a FieldSquare at a given position
     *
     * @param x X position of requested element
     * @param y Y position of requested element
     * @return FieldSquare at given position
     */
    FieldSquare elementAt(int x, int y) {
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
        copyColors();
        return lineToPlayerColor() + fillAreas();
    }

    /**
     * Changes dark red player line to cyan line
     *
     * @return Amount of squares that have been converted to cyan
     */
    private int lineToPlayerColor() {
        int count = 0;
        for (int x = 1; x < GameWorld.SQUARE_LENGTH - 1; x++)
            for (int y = 1; y < GameWorld.SQUARE_LENGTH - 1; y++)
                if (elementAt(x, y).getColor() == GameWorld.LINE_COLOR) {
                    elementAt(x, y).setColor(GameWorld.PLAYER_COLOR);
                    count++;
                }
        return count;
    }

    /**
     * Copies all fieldSquare colors to colors array
     */
    private void copyColors() {
        for (int i = 0; i < GameWorld.SQUARE_LENGTH; i++)
            for (int j = 0; j < GameWorld.SQUARE_LENGTH; j++)
                colors[i][j] = fieldSquares[i][j].getColor();
    }

    /**
     * Fills all areas except for the largest one.
     * Used for filling smaller half of field after player creates division
     *
     * @return Size of filled area(s)
     */
    private int fillAreas() {
        class Point3D {
            private int x, y, z;

            private Point3D(int x, int y, int z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }
        }
        ArrayList<Point3D> areas = new ArrayList<>();
        for (int x = 1; x < GameWorld.SQUARE_LENGTH - 1; x++)
            for (int y = 1; y < GameWorld.SQUARE_LENGTH - 1; y++)
                if (colors[x][y] == GameWorld.SQUARE_COLOR)
                    areas.add(new Point3D(x, y, areaSize(x, y)));

        if (areas.size() < 2)
            return 0;

        int max = 0;
        for (int i = 1; i < areas.size(); i++)
            if (areas.get(i).z > areas.get(max).z)
                max = i;
        areas.remove(max);

        int size = 0;
        for (Point3D area : areas) {
            floorFill(area.x, area.y);
            size += area.z;
        }
        return size;
    }

    /**
     * @param x Position x of area
     * @param y Position y of area
     * @return Size of area
     */
    private int areaSize(int x, int y) {
        int size = 0;

        Stack<Point> ps = new Stack<>();
        ps.push(new Point(x, y));

        while (ps.size() != 0) {
            Point cp = ps.pop();

            colors[cp.x][cp.y] = GameWorld.PLAYER_COLOR;

            if (colors[cp.x][cp.y + 1] == GameWorld.SQUARE_COLOR) {
                ps.push(cp);
                ps.push(new Point(cp.x, cp.y + 1));
                size++;
            } else if (colors[cp.x + 1][cp.y] == GameWorld.SQUARE_COLOR) {
                ps.push(cp);
                ps.push(new Point(cp.x + 1, cp.y));
                size++;
            } else if (colors[cp.x - 1][cp.y] == GameWorld.SQUARE_COLOR) {
                if (colors[cp.x][cp.y - 1] == GameWorld.SQUARE_COLOR)
                    ps.push(cp);
                ps.push(new Point(cp.x - 1, cp.y));
                size++;
            } else if (colors[cp.x][cp.y - 1] == GameWorld.SQUARE_COLOR) {
                ps.push(new Point(cp.x, cp.y - 1));
                size++;
            }
        }
        return size;
    }

    /**
     * Flood fills cyan from a given position
     *
     * @param x Starting location x
     * @param y Starting location y
     */
    private void floorFill(int x, int y) {
        if (elementAt(x, y).getColor() == GameWorld.PLAYER_COLOR)
            return;
        elementAt(x, y).setColor(GameWorld.PLAYER_COLOR);
        floorFill(x, y - 1);
        floorFill(x + 1, y);
        floorFill(x, y + 1);
        floorFill(x - 1, y);
    }
}
