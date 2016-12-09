package xonix.Controller;

import xonix.Model.GameWorld;
import xonix.Views.GameView;
import xonix.Views.MapView;
import xonix.Views.ScoreView;

public class GameController {
    private static GameController instance = null;
    public GameView view;
    public GameWorld model;

    private GameController() {
        ScoreView scoreView = new ScoreView();
        MapView mapView = new MapView();
        view = new GameView(scoreView, mapView);
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
