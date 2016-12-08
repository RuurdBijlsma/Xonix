package xonix.Commands;

import xonix.Application;
import xonix.Model.Car;

import java.awt.event.ActionEvent;

public class IncreaseSpeed extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        Car car = Application.controller.model.car;
        car.setSpeed(car.getSpeed() + 5);
    }
}
