package xonix;

import xonix.Commands.Tick;

import javax.swing.*;

public class GameController {
    private static GameController instance = null;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public GameView view;
    public GameWorld model;

    private GameController() {
        view = GameView.getInstance();
        model = GameWorld.getInstance();

        view.setWorld(model);

        play();
    }

    void setUserController(){
        new UserController();
    }

    /**
     * Starts game loop, calling update every tick
     */
    private void play() {
        new Timer(GameWorld.GAME_TICK_DELAY, Tick.getInstance()).start();
    }
}
