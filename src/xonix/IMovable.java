package xonix;

import java.awt.geom.Point2D;

public interface IMovable {
    Point2D loc = new Point2D() {
        @Override
        public double getX() {
            return 0;
        }

        @Override
        public double getY() {
            return 0;
        }

        @Override
        public void setLocation(double x, double y) {

        }
    };
    Point2D getLocation();
    int getHeading();
    float getSpeed();
    int getWidth();
    int getHeight();

    void setLocation(double x, double y);
    void setSpeed(float speed);
    void setWidth(int width);
    void setHeight(int height);
    void setHeading(int heading);
}
