package xonix;

class MonsterBall
{

    private java.awt.geom.Point2D.Float loc;
    private java.awt.Color color;
    private int heading;
    private float speed;
    private float radius;

    public MonsterBall (final java.awt.geom.Point2D.Float loc, final java.awt.Color color, final int heading, final float speed, final float radius)
    {
        setLocation (loc);
        setColor (color);
        setHeading (heading);
        setSpeed (speed);
        this.setRadius (radius);
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

    public void setHeading (final int heading)
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

    public float getRadius ()
    {
        return radius;
    }

    public void setRadius (float radius)
    {
        this.radius = radius;
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

    public boolean changeLocation (FieldSquares fss, State state, float delta)
    {
        java.awt.geom.Point2D.Float prev = getLocation ();
        java.awt.geom.Point2D.Float next = nextLocation (delta);
        FieldSquare fsprev = fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5));
        FieldSquare fsnext = fss.elementAt ((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5));

        if (fsprev.getColor () == GameWorld.LINE_COLOR || fsnext.getColor () == GameWorld.LINE_COLOR)
            return true;

        if (fsprev.getColor () != fsnext.getColor ())
        {
            if (fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor () != fss.elementAt ((int) (next.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor ())
                if (getHeading () < 180)
                    setHeading (180 - getHeading ());
                else
                    setHeading (540 - getHeading ());
            if (fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (prev.y / GameWorld.SQUARE_UNITS + 0.5)).getColor () != fss.elementAt ((int) (prev.x / GameWorld.SQUARE_UNITS + 0.5), (int) (next.y / GameWorld.SQUARE_UNITS + 0.5)).getColor ())
                setHeading (360 - getHeading ());
        }
        getLocation ().setLocation (nextLocation (delta));
        return false;
    }

    @Override
    public String toString ()
    {
        return "loc=" + loc.x + "," + loc.y + " color=[" + color.getRed () + "," + color.getGreen () + "," + color.getBlue () + "]" + " heading=" + heading + " speed=" + speed + " radius=" + radius;
    }
}
