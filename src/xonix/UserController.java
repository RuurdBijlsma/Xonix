package xonix;

import xonix.Commands.*;
import xonix.Views.GameView;

import javax.swing.*;
import java.awt.event.KeyEvent;

class UserController {
    private GameView view = Application.controller.view;

    UserController() {
        setInputKeys();
    }

    /**
     * Adds all input keys
     */
    private void setInputKeys() {
        addKey(KeyEvent.VK_UP, new GoNorth(), "Go North");
        addKey(KeyEvent.VK_LEFT, new GoWest(), "Go West");
        addKey(KeyEvent.VK_DOWN, new GoSouth(), "Go South");
        addKey(KeyEvent.VK_RIGHT, new GoEast(), "Go East");
        addKey(KeyEvent.VK_SPACE, new ResetGame(), "Reset Game");
        addKey(KeyEvent.VK_EQUALS, new IncreaseSpeed(), "Increase Speed");
        addKey(KeyEvent.VK_UNDERSCORE, new DecreaseSpeed(), "Decrease Speed");
        addKey(KeyEvent.VK_T, new AddTimeTicket(), "Add TimeTicket");
        addKey(KeyEvent.VK_M, new AddMonsterBall(), "Add MonsterBall");
        addKey(KeyEvent.VK_COMMA, new AddSmartMonsterBall(), "Add Smart MonsterBall");
        addKey(KeyEvent.VK_Q, new QuitGame(), "Quit Game");
        addKey(KeyEvent.VK_N, new NewGame(), "New Game");
        addKey(KeyEvent.VK_S, new SwitchStrategy(), "Switch All Bomb Strategy");
    }

    /**
     * @param key    ID of key, example: VK_UP
     * @param action Action to execute on keypress
     * @param name   Name of action
     */
    private void addKey(int key, AbstractAction action, String name) {
        view.map.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key, 0), name);
        view.map.getActionMap().put(name, action);
    }
}
