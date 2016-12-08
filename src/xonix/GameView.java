package xonix;

import xonix.Commands.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Main UI frame
 */
public class GameView extends JFrame implements Observer {
    private static GameView instance = null;
    private final ScoreView score;
    final MapView map;
    private GameWorld gameWorld;

    @Override
    public void update(Observable o, Object arg) {

    }

    private GameView() {
        this.gameWorld = null;
        this.setTitle("Xonix Game");
        JPanel all = new JPanel();

        this.add(all);
        this.setMenu();
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(516, 650));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        map = new MapView();
        all.add(map);

        score = new ScoreView();
        all.add(score);

        all.setBackground(Color.cyan);
        map.setBackground(Color.cyan);
        score.setBackground(Color.cyan);

        all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
        all.setBorder(new EmptyBorder(0, 0, 0, 0));

        map.setAlignmentX(CENTER_ALIGNMENT);
    }

    public static GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        }
        return instance;
    }

    /**
     * Creates menu
     */
    private void setMenu() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItem = new JMenuItem("New");
        menuItem.addActionListener(new NewGame());
        menu.add(menuItem);
//        menuItem = new JMenuItem("Save");
//        menu.add(menuItem);
//        menuItem = new JMenuItem("Undo");
//        menu.add(menuItem);
//        menuItem = new JMenuItem("Sound");
//        menu.add(menuItem);
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(new AboutGame());
        menu.add(menuItem);
        menuItem = new JMenuItem("Quit");
        menuItem.addActionListener(new QuitGame());
        menu.add(menuItem);
        menuBar.add(menu);
        menu = new JMenu("Command");
        menuItem = new JMenuItem("Add bomb");
        menuItem.addActionListener(new AddMonsterBall());
        menu.add(menuItem);
        menuItem = new JMenuItem("Add smartbomb");
        menuItem.addActionListener(new AddSmartMonsterBall());
        menu.add(menuItem);
        menuItem = new JMenuItem("Add timeticket");
        menuItem.addActionListener(new AddTimeTicket());
        menu.add(menuItem);
        menuItem = new JMenuItem("Switch bombstrategies");
        menuItem.addActionListener(new SwitchStrategy());
        menu.add(menuItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    void setWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Update score and map
     */
    public void updateAll() {
        score.update();
        map.update();
    }

    /**
     * Panel containing score information
     */
    public class ScoreView extends JPanel {
        final private JLabel level;
        final private JLabel time;
        final private JLabel lives;
        final private JLabel currentScore;

        ScoreView() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            level = new JLabel("");
            this.add(level);
            this.add(Box.createHorizontalGlue());
            time = new JLabel("");
            this.add(time);
            this.add(Box.createHorizontalGlue());
            lives = new JLabel("");
            this.add(lives);
            this.add(Box.createHorizontalGlue());
            currentScore = new JLabel("");
            this.add(currentScore);
            this.add(Box.createHorizontalGlue());

            Color color = new Color(10, 10, 10);

            currentScore.setForeground(color);
            lives.setForeground(color);
            time.setForeground(color);
            level.setForeground(color);

            Font font = new Font("Consolas", Font.BOLD, 14);

            currentScore.setFont(font);
            lives.setFont(font);
            time.setFont(font);
            level.setFont(font);
        }

        /**
         * Shows updated stats in the labels
         */
        public void update() {
            this.level.setText("Level: " + gameWorld.state.getLevel());
            this.time.setText("Time:  " + (int) gameWorld.state.getClock());
            this.lives.setText("Lives: " + gameWorld.state.getLives());
            this.currentScore.setText("Score: " + gameWorld.state.getCurrentScore() + " / " + gameWorld.state.getRequiredScore());
        }
    }

    public class MapView extends JPanel {

        /**
         * Repaints mapview
         */
        void update() {
            this.repaint();
        }

        /**
         * Renders world, gameover text, monsterballs, timetickets and car
         */
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D G2D = (Graphics2D) g;
            G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            G2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            for (int x = 0; x < GameWorld.SQUARE_LENGTH; x++)
                for (int y = 0; y < GameWorld.SQUARE_LENGTH; y++) {
                    FieldSquare fieldSquare = gameWorld.fieldSquares.elementAt(x, y);
                    G2D.setColor(fieldSquare.getColor());
                    G2D.fillRect((int) fieldSquare.getLocation().x, (int) fieldSquare.getLocation().y, (int) fieldSquare.getSize(), (int) fieldSquare.getSize());
                }

            if (gameWorld.state.isGameOver()) {
                Font font = new Font("Consolas", Font.BOLD, 50);
                FontMetrics metrics = G2D.getFontMetrics(font);
                G2D.setColor(Color.RED);
                G2D.setFont(font);
                G2D.drawString("GAME OVER", (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - metrics.stringWidth("GAME OVER")) / 2, (GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - metrics.getHeight()) / 2);
                return;
            }

            for (MonsterBall monsterBall : gameWorld.monsterBalls) {
                G2D.setColor(monsterBall.getColor());
                G2D.fillArc((int) monsterBall.getLocation().x, (int) monsterBall.getLocation().y, (int) monsterBall.getRadius(), (int) monsterBall.getRadius(), 0, 360);
            }

            for (TimeTicket timeTicket : gameWorld.timeTickets) {
                G2D.setColor(timeTicket.getColor());
                G2D.fillRect((int) timeTicket.getLocation().x, (int) timeTicket.getLocation().y, timeTicket.getWidth(), timeTicket.getHeight());
            }

            G2D.setColor(gameWorld.car.getColor());
            G2D.fillRect((int) gameWorld.car.getLocation().x, (int) gameWorld.car.getLocation().y, gameWorld.car.getWidth(), gameWorld.car.getHeight());
        }
    }
}
