package xonix.Controller;

import xonix.Model.GameWorld;
import xonix.Views.GameView;

public class GameController {
    private static GameController instance = null;
    public GameView view;
    public GameWorld model;
    private GameController() {
        view = GameView.getInstance();
        model = GameWorld.getInstance();
        model.addObserver(view);

        model.play();
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void initialize() {
        new UserController();
        model.fillField();
    }
}
