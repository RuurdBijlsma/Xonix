package xonix.Views;

import xonix.Model.GameWorld;
import xonix.Model.State;

import javax.swing.*;
import java.awt.*;

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
    public void update(GameWorld world) {
        State state = world.state;
        this.level.setText("Level: " + state.getLevel());
        this.time.setText("Time:  " + (int) state.getClock());
        this.lives.setText("Lives: " + state.getLives());
        this.currentScore.setText("Score: " + state.getCurrentScore() + " / " + state.getRequiredScore());
    }
}