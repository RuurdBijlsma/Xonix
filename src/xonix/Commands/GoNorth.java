package xonix.Commands;

import xonix.Application;

import java.awt.event.ActionEvent;

public class GoNorth extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        Application.controller.model.car.setHeading(90);
    }
}
