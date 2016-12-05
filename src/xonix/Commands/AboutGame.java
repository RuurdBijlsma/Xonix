package xonix.Commands;

import xonix.GameView;
import xonix.TextPopup;

import java.awt.event.ActionEvent;

public class AboutGame extends Command {
    private GameView view;

    @Override
    public void actionPerformed(ActionEvent e) {
        new TextPopup("About Xonix", "Xonix game by Albert Sikkema\nCode and looks improved by Ruurd Bijlsma\nCreate as many cyan squares as possible\nBut don't let the time run out!");
    }
}
