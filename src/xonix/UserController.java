package xonix;

import xonix.Commands.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

class UserController {
    private Random random = new Random();

    UserController() {
        Application.controller.view.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                execute(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * Handles key events
     *
     * @param keycode code of pressed key
     */
    private void execute(int keycode) {
        GameWorld model = Application.controller.model;
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                GoWest goWest = new GoWest();
                goWest.actionPerformed(new ActionEvent(this, 0, "GoWest"));
                break;
            case KeyEvent.VK_UP:
                GoNorth goNorth = new GoNorth();
                goNorth.actionPerformed(new ActionEvent(this, 0, "GoNorth"));
                break;
            case KeyEvent.VK_RIGHT:
                GoEast goEast = new GoEast();
                goEast.actionPerformed(new ActionEvent(this, 0, "GoEast"));
                break;
            case KeyEvent.VK_DOWN:
                GoSouth goSouth = new GoSouth();
                goSouth.actionPerformed(new ActionEvent(this, 0, "GoSouth"));
                break;
            case KeyEvent.VK_SPACE:
                if (model.state.isGameOver())
                    model.reset();
                break;
            case KeyEvent.VK_I:
                IncreaseSpeed increaseSpeed = new IncreaseSpeed();
                increaseSpeed.actionPerformed(new ActionEvent(this, 0, "IncreaseSpeed"));
                break;
            case KeyEvent.VK_K:
                AddTimeTicket addTimeTicket = new AddTimeTicket();
                addTimeTicket.actionPerformed(new ActionEvent(this, 0, "AddTimeTicket"));
                break;
            case KeyEvent.VK_L:
                DecreaseSpeed decreaseSpeed = new DecreaseSpeed();
                decreaseSpeed.actionPerformed(new ActionEvent(this, 0, "DecreaseSpeed"));
                break;
            case KeyEvent.VK_M:
                AddMonsterBall addMonsterBall = new AddMonsterBall();
                addMonsterBall.actionPerformed(new ActionEvent(this, 0, "AddMonsterBall"));
                break;
            case KeyEvent.VK_Q:
                QuitGame quitGame = new QuitGame();
                quitGame.actionPerformed(new ActionEvent(this, 0, "QuitGame"));
                break;
        }
    }
}
