package xonix;

class Car
{
    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    private int heading;
    private float speed;
    private int width;
    private int height;

    public Car (final java.awt.geom.Point2D.Float loc, final java.awt.Color color, final int heading, final int speed, final int width, final int height)
    {
        setLocation (loc);
        setColor (color);
        setHeading (heading);
        setSpeed (speed);
        setWidth (width);
        setHeight (height);
    }

    public void reset ()
    {
        setLocation (new java.awt.geom.Point2D.Float (GameWorld.SQUARE_LENGTH / 2 * GameWorld.SQUARE_UNITS, (GameWorld.SQUARE_LENGTH - 1) * GameWorld.SQUARE_UNITS));
        setColor (GameWorld.CAR_COLOR);
        setHeading (270);
        setSpeed (50);
        setWidth (GameWorld.SQUARE_UNITS);
        setHeight (GameWorld.SQUARE_UNITS);
    }

    public java.awt.geom.Point2D.Float getLocation ()
    {
        return loc;
    }

    public void setLocation (java.awt.geom.Point2D.Float loc)
    {
        this.loc = loc;
    }

    public java.awt.Color getColor ()
    {
        return color;
    }

    public void setColor (final java.awt.Color color)
    {
        this.color = color;
    }

    public int getHeading ()
    {
        return heading;
    }

    public final void setHeading (final int heading)
    {
        this.heading = heading;
    }

    public float getSpeed ()
    {
        return speed;
    }

    public final void setSpeed (final float speed)
    {
        this.speed = speed;
    }

    public int getWidth ()
    {
        return width;
    }

    public final void setWidth (final int width)
    {
        this.width = width;
    }

    public int getHeight ()
    {
        return height;
    }

    public final void setHeight (final int height)
    {
        this.height = height;
    }

    public java.awt.geom.Point2D.Float nextLocation (float delta)
    {
        double radians = Math.toRadians (getHeading ());
        float newx = getLocation ().x + delta * getSpeed () * (float) Math.cos (radians);
        if (newx < 0)
            newx = 0;
        else if (newx > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newx = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        float newy = getLocation ().y - delta * getSpeed () * (float) Math.sin (radians);
        if (newy < 0)
            newy = 0;
        else if (newy > GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1))
            newy = GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - (GameWorld.SQUARE_UNITS - 1);
        return new java.awt.geom.Point2D.Float (newx, newy);
    }

    public void changeLocation (FieldSquares fss, State state, float delta)
    {
        java.awt.geom.Point2D.Float prev = getLocation ();
        java.awt.geom.Point2D.Float next = nextLocation (delta);
        FieldSquare fsprev = fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5));
        FieldSquare fsnext = fss.elementAt ((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5));
        if (fsnext.getColor () == GameWorld.SQUARE_COLOR)
            fsnext.setColor (GameWorld.LINE_COLOR);
        else if (fsnext.getColor () == GameWorld.PLAYER_COLOR && fsprev.getColor () == GameWorld.LINE_COLOR)
            state.addcscore (fss.fillSquares ());
        getLocation ().setLocation (next);
    }

    @Override
    public String toString ()
    {
        return "loc=" + getLocation ().x + "," + getLocation ().y + " color=[" + color.getRed () + "," + color.getGreen () + "," + color.getBlue () + "]" + " heading=" + heading + " speed=" + speed + " width=" + width + " height=" + height;
    }
}
