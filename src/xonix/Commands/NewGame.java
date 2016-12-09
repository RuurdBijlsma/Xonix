package xonix.Commands;

import xonix.Application;

import java.awt.event.ActionEvent;

public class NewGame extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        ResetGame resetter = new ResetGame();
        resetter.actionPerformed(null);
        Application.controller.model.state.setLevel(1);
    }
}
