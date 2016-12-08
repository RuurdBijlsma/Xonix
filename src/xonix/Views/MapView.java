package xonix.Views;

import xonix.Model.FieldSquare;
import xonix.Model.GameWorld;
import xonix.Model.MonsterBall;
import xonix.Model.TimeTicket;

import javax.swing.*;
import java.awt.*;

public class MapView extends JPanel {
    private GameWorld gameWorld;
    void setWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

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