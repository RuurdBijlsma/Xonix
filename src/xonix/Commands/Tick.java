package xonix.Commands;

import xonix.Application;
import xonix.Model.GameWorld;

import java.awt.event.ActionEvent;

public class Tick extends Command {
    private static Tick instance = null;

    public static Tick getInstance() {
        if (instance == null) {
            instance = new Tick();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application.controller.model.update((float) (GameWorld.GAME_TICK_DELAY / 1000.0));
        Application.controller.view.updateAll();
    }
}
