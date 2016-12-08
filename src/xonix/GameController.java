package xonix;

import xonix.Model.GameWorld;
import xonix.Views.GameView;

public class GameController {
    private static GameController instance = null;
    public GameView view;
    public GameWorld model;
    private GameController() {
        view = GameView.getInstance();
        model = GameWorld.getInstance();

        view.setWorld(model);

        model.play();
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    void initialize() {
        new UserController();
        model.fillField();
    }
}
