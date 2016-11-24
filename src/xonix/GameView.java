package xonix;

public class GameView extends javax.swing.JFrame
{
    class ScoreView extends javax.swing.JPanel
    {
        final private javax.swing.JLabel level;
        final private javax.swing.JLabel time;
        final private javax.swing.JLabel lives;
        final private javax.swing.JLabel cscore;
        final private javax.swing.JLabel rscore;

        public ScoreView ()
        {
            this.setLayout (new javax.swing.BoxLayout (this, javax.swing.BoxLayout.X_AXIS));
            this.setBorder (javax.swing.BorderFactory.createEmptyBorder (10, 0, 10, 0));
            level = new javax.swing.JLabel ("");
            this.add (level);
            this.add (javax.swing.Box.createHorizontalGlue ());
            time = new javax.swing.JLabel ("");
            this.add (time);
            this.add (javax.swing.Box.createHorizontalGlue ());
            lives = new javax.swing.JLabel ("");
            this.add (lives);
            this.add (javax.swing.Box.createHorizontalGlue ());
            cscore = new javax.swing.JLabel ("");
            this.add (cscore);
            this.add (javax.swing.Box.createHorizontalGlue ());
            rscore = new javax.swing.JLabel ("");
            this.add (rscore);
        }

        public void update ()
        {
            this.level.setText ("Current level: " + gw.state.getLevel ());
            this.time.setText ("Remaining time: " + (int) gw.state.getClock ());
            this.lives.setText ("Lives left: " + gw.state.getLives ());
            this.cscore.setText ("Current score: " + gw.state.getcscore ());
            this.rscore.setText ("Required score: " + gw.state.getrscore ());
        }
    }

    class MapView extends javax.swing.JPanel
    {

        public MapView ()
        {
            super ();
        }

        public void update ()
        {
            this.repaint ();
        }

        @Override
        public void paint (java.awt.Graphics g)
        {
            super.paint (g);

            for (int i = 0; i < gw.SQUARE_LENGTH; i ++)
                for (int j = 0; j < gw.SQUARE_LENGTH; j ++)
                {
                    FieldSquare fs = gw.fss.elementAt (i, j);
                    g.setColor (fs.getColor ());
                    g.fillRect ((int) fs.getLocation ().x, (int) fs.getLocation ().y, (int) fs.getSize (), (int) fs.getSize ());
                }

            if (gw.state.isGameOver ())
            {
                java.awt.Font font = new java.awt.Font ("Helvetica", java.awt.Font.BOLD, 18);
                java.awt.FontMetrics metrics = g.getFontMetrics (font);
                g.setColor (java.awt.Color.RED);
                g.setFont (font);
                g.drawString ("GAME OVER", (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - metrics.stringWidth ("GAME OVER")) / 2, (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - metrics.getHeight ()) / 2);
                return;
            }

            for (MonsterBall mb : gw.mbs)
            {
                g.setColor (mb.getColor ());
                g.fillArc ((int) mb.getLocation ().x, (int) (int) mb.getLocation ().y, (int) mb.getRadius (), (int) mb.getRadius (), 0, 360);
            }

            for (TimeTicket tt : gw.tts)
            {
                g.setColor (tt.getColor ());
                g.fillRect ((int) tt.getLocation ().x, (int) tt.getLocation ().y, (int) tt.getWidth (), (int) tt.getHeight ());
            }

            g.setColor (gw.car.getColor ());
            g.fillRect ((int) gw.car.getLocation ().x, (int) gw.car.getLocation ().y, gw.car.getWidth (), gw.car.getHeight ());
        }
    }

    private GameWorld gw;
    private final javax.swing.JPanel all;
    public final ScoreView score;
    public final MapView map;

    public GameView ()
    {
        this.gw = null;
        this.setTitle ("Xonix Game");
        all = new javax.swing.JPanel ();
        all.setLayout (new javax.swing.BoxLayout (all, javax.swing.BoxLayout.Y_AXIS));
        all.setBorder (new javax.swing.border.EmptyBorder (0, 30, 0, 30));
        score = new ScoreView ();
        all.add (score);
        map = new MapView ();
        map.setAlignmentX (CENTER_ALIGNMENT);
        all.add (map);
        this.add (all);
        this.setMenu ();
        this.pack ();
        this.setLocationRelativeTo (null);
        this.setDefaultCloseOperation (javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize (new java.awt.Dimension (630, 610));
        this.setResizable (false);
        this.setVisible (true);
    }

    private void setMenu ()
    {
        javax.swing.JMenuBar menuBar;
        javax.swing.JMenu menu;
        javax.swing.JMenuItem menuItem;
        menuBar = new javax.swing.JMenuBar ();
        menu = new javax.swing.JMenu ("File");
        menuItem = new javax.swing.JMenuItem ("New");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Save");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Undo");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Sound");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("About");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Quit");
        menu.add (menuItem);
        menuBar.add (menu);
        menu = new javax.swing.JMenu ("Command");
        menuItem = new javax.swing.JMenuItem ("Add bomb");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Add smartbomb");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Add timeticket");
        menu.add (menuItem);
        menuItem = new javax.swing.JMenuItem ("Switch bombstrategies ");
        menu.add (menuItem);
        menuBar.add (menu);
        this.setJMenuBar (menuBar);
    }

    public void setWorld (GameWorld gw)
    {
        this.gw = gw;
    }

    public void update ()
    {
        score.update ();
        map.update ();
    }
}
