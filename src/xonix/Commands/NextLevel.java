package xonix.Commands;

import xonix.Application;
import xonix.Model.RealState;

import java.awt.event.ActionEvent;

public class NextLevel extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        ResetGame resetGame = new ResetGame();
        resetGame.actionPerformed(new ActionEvent(this, 0, "ResetGame"));
        RealState state = Application.controller.model.state;
        state.setLevel(state.getLevel() + 1);
    }
}
