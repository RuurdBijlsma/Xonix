package xonix;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Stack;

class FieldSquares {

    private final FieldSquare[][] fsss;
    private final Color[][] colors;

    FieldSquares() {
        fsss = new FieldSquare[GameWorld.SQUARE_LENGTH][GameWorld.SQUARE_LENGTH];
        setFields(GameWorld.PLAYER_COLOR, GameWorld.SQUARE_COLOR);
        colors = new Color[GameWorld.SQUARE_LENGTH][GameWorld.SQUARE_LENGTH];
    }

    FieldSquare elementAt(int i, int j) {
        return fsss[i][j];
    }

    void reset() {
        setFields(GameWorld.PLAYER_COLOR, GameWorld.SQUARE_COLOR);
    }

    private void setFields(Color edge, Color inner) {
        for (int y = 0; y < GameWorld.SQUARE_LENGTH; y++)
            for (int x = 0; x < GameWorld.SQUARE_LENGTH; x++)
                if (x == 0 || y == 0 || x == GameWorld.SQUARE_LENGTH - 1 || y == GameWorld.SQUARE_LENGTH - 1)
                    fsss[x][y] = new FieldSquare(new Point2D.Float(x * GameWorld.SQUARE_UNITS, y * GameWorld.SQUARE_UNITS), edge, GameWorld.SQUARE_UNITS);
                else
                    fsss[x][y] = new FieldSquare(new Point2D.Float(x * GameWorld.SQUARE_UNITS, y * GameWorld.SQUARE_UNITS), inner, GameWorld.SQUARE_UNITS);
    }

    int fillSquares() {
        copyColors();
        return lineToPlayerColor() + fillAreas();
    }

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

    private void copyColors() {
        for (int i = 0; i < GameWorld.SQUARE_LENGTH; i++)
            for (int j = 0; j < GameWorld.SQUARE_LENGTH; j++)
                colors[i][j] = fsss[i][j].getColor();
    }

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
            floodfill(area.x, area.y);
            size += area.z;
        }
        return size;
    }

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

    private void floodfill(int x, int y) {
        if (elementAt(x, y).getColor() == GameWorld.PLAYER_COLOR)
            return;
        elementAt(x, y).setColor(GameWorld.PLAYER_COLOR);
        floodfill(x, y - 1);
        floodfill(x + 1, y);
        floodfill(x, y + 1);
        floodfill(x - 1, y);
    }
}
