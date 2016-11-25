package xonix;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class GameView extends JFrame {
    class ScoreView extends JPanel {
        final private JLabel level;
        final private JLabel time;
        final private JLabel lives;
        final private JLabel cscore;
        final private JLabel rscore;

        ScoreView() {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            level = new JLabel("");
            this.add(level);
            this.add(Box.createHorizontalGlue());
            time = new JLabel("");
            this.add(time);
            this.add(Box.createHorizontalGlue());
            lives = new JLabel("");
            this.add(lives);
            this.add(Box.createHorizontalGlue());
            cscore = new JLabel("");
            this.add(cscore);
            this.add(Box.createHorizontalGlue());
            rscore = new JLabel("");
            this.add(rscore);
        }

        void update() {
            this.level.setText("Current level: " + gw.state.getLevel());
            this.time.setText("Remaining time: " + (int) gw.state.getClock());
            this.lives.setText("Lives left: " + gw.state.getLives());
            this.cscore.setText("Current score: " + gw.state.getcscore());
            this.rscore.setText("Required score: " + gw.state.getrscore());
        }
    }

    class MapView extends JPanel {

        MapView() {
            super();
        }

        void update() {
            this.repaint();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            for (int i = 0; i < GameWorld.SQUARE_LENGTH; i++)
                for (int j = 0; j < GameWorld.SQUARE_LENGTH; j++) {
                    FieldSquare fs = gw.fss.elementAt(i, j);
                    g.setColor(fs.getColor());
                    g.fillRect((int) fs.getLocation().x, (int) fs.getLocation().y, (int) fs.getSize(), (int) fs.getSize());
                }

            if (gw.state.isGameOver()) {
                Font font = new Font("Helvetica", Font.BOLD, 18);
                FontMetrics metrics = g.getFontMetrics(font);
                g.setColor(Color.RED);
                g.setFont(font);
                g.drawString("GAME OVER", (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - metrics.stringWidth("GAME OVER")) / 2, (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - metrics.getHeight()) / 2);
                return;
            }

            for (MonsterBall mb : gw.mbs) {
                g.setColor(mb.getColor());
                g.fillArc((int) mb.getLocation().x, (int) mb.getLocation().y, (int) mb.getRadius(), (int) mb.getRadius(), 0, 360);
            }

            for (TimeTicket tt : gw.tts) {
                g.setColor(tt.getColor());
                g.fillRect((int) tt.getLocation().x, (int) tt.getLocation().y, (int) tt.getWidth(), (int) tt.getHeight());
            }

            g.setColor(gw.car.getColor());
            g.fillRect((int) gw.car.getLocation().x, (int) gw.car.getLocation().y, gw.car.getWidth(), gw.car.getHeight());
        }
    }

    private GameWorld gw;
    public final ScoreView score;
    private final MapView map;

    GameView() {
        this.gw = null;
        this.setTitle("Xonix Game");
        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        all.setBorder(new EmptyBorder(0, 30, 0, 30));
        score = new ScoreView();
        all.add(score);
        map = new MapView();
        map.setAlignmentX(CENTER_ALIGNMENT);
        all.add(map);
        this.add(all);
        this.setMenu();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(630, 610));
        this.setResizable(false);
        this.setVisible(true);
    }

    private void setMenu() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItem = new JMenuItem("New");
        menu.add(menuItem);
        menuItem = new JMenuItem("Save");
        menu.add(menuItem);
        menuItem = new JMenuItem("Undo");
        menu.add(menuItem);
        menuItem = new JMenuItem("Sound");
        menu.add(menuItem);
        menuItem = new JMenuItem("About");
        menu.add(menuItem);
        menuItem = new JMenuItem("Quit");
        menu.add(menuItem);
        menuBar.add(menu);
        menu = new JMenu("Command");
        menuItem = new JMenuItem("Add bomb");
        menu.add(menuItem);
        menuItem = new JMenuItem("Add smartbomb");
        menu.add(menuItem);
        menuItem = new JMenuItem("Add timeticket");
        menu.add(menuItem);
        menuItem = new JMenuItem("Switch bombstrategies ");
        menu.add(menuItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    public void setWorld(GameWorld gw) {
        this.gw = gw;
    }

    public void update() {
        score.update();
        map.update();
    }
}
