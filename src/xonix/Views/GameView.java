package xonix.Views;

import xonix.Commands.*;
import xonix.Model.GameWorld;

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
    public final ScoreView score;
    public final MapView map;

    private GameView() {
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

    @Override
    public void update(Observable o, Object arg) {
        update((GameWorld)o);
    }

    /**
     * Update score and map
     */
    public void update(GameWorld world) {
        score.update(world.state.getProxy());
        map.update(world);
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
}
