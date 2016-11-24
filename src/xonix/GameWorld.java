package xonix;

public class GameWorld
{

    static final int SQUARE_LENGTH = 102;
    static final int SQUARE_UNITS = 5;
    static final int GAME_TICK_DELAY = 40;
    static final java.awt.Color NO_COLOR = java.awt.Color.white;
    static final java.awt.Color CAR_COLOR = java.awt.Color.red;
    static final java.awt.Color SQUARE_COLOR = java.awt.Color.black;
    static final java.awt.Color LINE_COLOR = java.awt.Color.red.darker ().darker ();
    static final java.awt.Color PLAYER_COLOR = java.awt.Color.cyan;
    static final java.awt.Color MONSTER_COLOR = java.awt.Color.orange;
    static final java.awt.Color TICKET_COLOR = java.awt.Color.green;
    static final int LEVEL_START = 1;
    static final int CLOCK_START = (6 - LEVEL_START) * 2;
    static final int LIVES_START = 3;
    static final int CSCORE_START = 0;
    static final int RSCORE_START = (40 + LEVEL_START * 10) * 100;
    static final int TTIME_START = 6 - LEVEL_START;

    public final GameView gv;
    public final FieldSquares fss;
    public java.util.ArrayList<MonsterBall> mbs;
    public java.util.ArrayList<TimeTicket> tts;
    public final Car car;
    public State state;
    private final java.util.Random random;

    public GameWorld ()
    {
        this.random = new java.util.Random ();
        this.gv = new GameView ();
        this.gv.setWorld (this);
        this.fss = new FieldSquares ();
        createMonsterballs ();
        createTimeTickets ();
        this.car = new Car (new java.awt.geom.Point2D.Float (SQUARE_LENGTH / 2 * SQUARE_UNITS, (SQUARE_LENGTH - 1) * SQUARE_UNITS), CAR_COLOR, 270, 50, SQUARE_UNITS, SQUARE_UNITS);
        this.state = new State ();
        gv.addKeyListener (new java.awt.event.KeyListener ()
        {
            @Override
            public void keyTyped (java.awt.event.KeyEvent e)
            {
            }

            @Override
            public void keyPressed (java.awt.event.KeyEvent e)
            {
                execute (e.getKeyCode ());
            }

            @Override
            public void keyReleased (java.awt.event.KeyEvent e)
            {
            }
        });
        this.play ();
    }

    public void createMonsterballs ()
    {
        this.mbs = new java.util.ArrayList<> ();
        int number = random.nextInt (10) + 1;
        for (int i = 0; i < number; i ++)
            mbs.add (new MonsterBall (new java.awt.geom.Point2D.Float (random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), MONSTER_COLOR, random.nextInt (360), random.nextFloat () * 100 + 10, 6));
    }

    public void createTimeTickets ()
    {
        this.tts = new java.util.ArrayList<> ();
        int number = random.nextInt (SQUARE_UNITS) + 1;
        for (int i = 0; i < number; i ++)
            tts.add (new TimeTicket (new java.awt.geom.Point2D.Float (random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), TICKET_COLOR, TTIME_START, 7, 7));
    }

    public void play ()
    {
        gv.score.update ();
        new javax.swing.Timer (GAME_TICK_DELAY, new java.awt.event.ActionListener ()
        {
            @Override
            public void actionPerformed (java.awt.event.ActionEvent evt)
            {
                update ((float) (GAME_TICK_DELAY / 1000.0));
            }
        }).start ();
    }

    public void update (float delta)
    {
        if ( ! state.isGameOver ())
        {
            state.addClock ( - delta);
            for (MonsterBall mb : mbs)
                if (mb.changeLocation (fss, state, delta))
                {
                    state.decLives ();
                    mbs.remove (mb);
                    break;
                }
            car.changeLocation (fss, state, delta);
            for (TimeTicket tt : tts)
                if (tt.contains (car.getLocation ()))
                {
                    state.setClock (state.getClock () + tt.getSeconds ());
                    tts.remove (tt);
                    gv.score.update ();
                    break;
                }
        }
        gv.update ();
    }

    public void reset ()
    {
        this.fss.reset ();
        createMonsterballs ();
        createTimeTickets ();
        this.car.reset ();
        this.state.reset ();
    }

    public void execute (int keycode)
    {
        switch (keycode)
        {
            case java.awt.event.KeyEvent.VK_LEFT:
                car.setHeading (180);
                break;
            case java.awt.event.KeyEvent.VK_UP:
                car.setHeading (90);
                break;
            case java.awt.event.KeyEvent.VK_RIGHT:
                car.setHeading (0);
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                car.setHeading (270);
                break;
            case java.awt.event.KeyEvent.VK_SPACE:
                if (state.isGameOver ())
                    reset ();
                break;
            case java.awt.event.KeyEvent.VK_I:
                car.setSpeed (car.getSpeed () + 5);
                break;
            case java.awt.event.KeyEvent.VK_K:
                tts.add (new TimeTicket (new java.awt.geom.Point2D.Float (random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), TICKET_COLOR, TTIME_START, 7, 7));
                break;
            case java.awt.event.KeyEvent.VK_L:
                car.setSpeed (car.getSpeed () - 5);
                break;
            case java.awt.event.KeyEvent.VK_M:
                mbs.add (new MonsterBall (new java.awt.geom.Point2D.Float (random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt (SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), MONSTER_COLOR, random.nextInt (360), random.nextFloat () * 100 + 10, 6));
                break;
        }
    }
}
