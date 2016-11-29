package xonix;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Timeticket class which contains location, color, size and seconds value
 */
class TimeTicket extends BaseObject{
    private int seconds;

    TimeTicket(final Point2D.Float loc, final Color color, int seconds, final int width, final int height) {
        setLocation(loc);
        setColor(color);
        setWidth(width);
        setHeight(height);
        setSeconds(seconds);
    }

    int getSeconds() {
        return seconds;
    }

    private void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * @return String containing timeticket information
     */
    @Override
    public String toString() {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" + " width=" + width + " height=" + height + " seconds=" + seconds;
    }
}
