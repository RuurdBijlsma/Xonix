package xonix;

import javax.swing.*;

class GameController {
    private static GameController instance = null;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private GameView view;
    private GameWorld model;

    private GameController() {
        view = GameView.getInstance();
        model = GameWorld.getInstance();

        view.setWorld(model);

        new UserController(view, model);

        play();
    }

    /**
     * Starts gameloop, calling update every tick
     */
    private void play() {
        new Timer(GameWorld.GAME_TICK_DELAY, evt -> {
            model.update((float) (GameWorld.GAME_TICK_DELAY / 1000.0));
            view.update();
        }).start();
    }
}
