package xonix.Commands;

import xonix.Application;
import xonix.Model.FieldSquares;
import xonix.Model.GameWorld;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Stack;

public class AddSquareGroup extends Command {
    public int filledSize = 0;
    private FieldSquares fieldSquares;

    @Override
    public void actionPerformed(ActionEvent e) {
        fieldSquares = Application.controller.model.fieldSquares;
        fieldSquares.copyColors();
        filledSize = lineToPlayerColor() + fillAreas();
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
                if (fieldSquares.elementAt(x, y).getColor() == GameWorld.LINE_COLOR) {
                    fieldSquares.elementAt(x, y).setColor(GameWorld.PLAYER_COLOR);
                    count++;
                }
        return count;
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
                if (fieldSquares.colors[x][y] == GameWorld.SQUARE_COLOR)
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

            fieldSquares.colors[cp.x][cp.y] = GameWorld.PLAYER_COLOR;

            if (fieldSquares.colors[cp.x][cp.y + 1] == GameWorld.SQUARE_COLOR) {
                ps.push(cp);
                ps.push(new Point(cp.x, cp.y + 1));
                size++;
            } else if (fieldSquares.colors[cp.x + 1][cp.y] == GameWorld.SQUARE_COLOR) {
                ps.push(cp);
                ps.push(new Point(cp.x + 1, cp.y));
                size++;
            } else if (fieldSquares.colors[cp.x - 1][cp.y] == GameWorld.SQUARE_COLOR) {
                if (fieldSquares.colors[cp.x][cp.y - 1] == GameWorld.SQUARE_COLOR)
                    ps.push(cp);
                ps.push(new Point(cp.x - 1, cp.y));
                size++;
            } else if (fieldSquares.colors[cp.x][cp.y - 1] == GameWorld.SQUARE_COLOR) {
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
        if (fieldSquares.elementAt(x, y).getColor() == GameWorld.PLAYER_COLOR)
            return;
        fieldSquares.elementAt(x, y).setColor(GameWorld.PLAYER_COLOR);
        floorFill(x, y - 1);
        floorFill(x + 1, y);
        floorFill(x, y + 1);
        floorFill(x - 1, y);
    }
}
