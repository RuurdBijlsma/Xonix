package xonix;

class State
{
    private int level;
    private float clock;
    private int lives;
    private int cscore;
    private int rscore;
    private int tTime;
    private boolean gameOver;

    State ()
    {
        this.reset ();
    }

    public void reset ()
    {
        setLevel (1);
    }

    public int getLevel ()
    {
        return level;
    }

    public void setLevel (int level)
    {
        this.level = level;
        this.clock = (6 - level) * 2;
        this.lives = 3;
        this.cscore = 0;
        this.rscore = (40 + level * 10) * 100;
        this.tTime = level - 1;
        this.gameOver = false;
    }

    public float getClock ()
    {
        return clock;
    }

    public void setClock (float clock)
    {
        this.clock = clock;
        if ((int)clock == 0)
        {
            decLives ();
            this.clock = (6 - level) * 2;
        }
    }

    public void addClock (float clock)
    {
        setClock (this.clock + clock);
    }

    public int getLives ()
    {
        return lives;
    }

    public void setLives (int lives)
    {
        this.lives = lives;
    }

    public void decLives ()
    {
        setLives (getLives () - 1);
        if (getLives () == 0)
            gameOver = true;
    }

    public int getcscore ()
    {
        return cscore;
    }

    public void setcscore (int cscore)
    {
        this.cscore = cscore;
        if (cscore > rscore)
            setLevel (level + 1);
    }

    public void addcscore (int cscore)
    {
        setcscore (this.cscore + cscore);
    }

    public int getrscore ()
    {
        return rscore;
    }

    public void setrscore (int rscore)
    {
        this.rscore = rscore;
    }

    public boolean isGameOver ()
    {
        return gameOver;
    }

    public void setGameOver (boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    @Override
    public String toString ()
    {
        return "Current level=" + this.getLevel ()
                + " Remaining lives=" + this.getLives ()
                + " Remaining time=" + this.getClock ()
                + " Current score=" + this.getcscore ()
                + " Required score=" + this.getrscore ();
    }
}
