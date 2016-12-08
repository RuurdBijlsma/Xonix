package xonix.Views;

import xonix.Application;
import xonix.Model.GameWorld;

import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel {
    final private JLabel level;
    final private JLabel time;
    final private JLabel lives;
    final private JLabel currentScore;
    private GameWorld gameWorld;

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
        this.level.setText("Level: " + Application.controller.model.state.getLevel());
        this.time.setText("Time:  " + (int) gameWorld.state.getClock());
        this.lives.setText("Lives: " + gameWorld.state.getLives());
        this.currentScore.setText("Score: " + gameWorld.state.getCurrentScore() + " / " + gameWorld.state.getRequiredScore());
    }

    void setWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }
}