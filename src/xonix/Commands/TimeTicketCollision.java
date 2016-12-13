package xonix.Commands;

import xonix.Model.GameWorld;
import xonix.Model.Sound;
import xonix.Model.TimeTicket;

import java.awt.event.ActionEvent;

public class TimeTicketCollision extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameWorld model = (GameWorld) e.getSource();

        for (TimeTicket timeTicket : model.timeTickets)
            if (timeTicket.contains(model.car.getLocation())) {
                model.soundManager.play(Sound.TIMETICKET);
                model.state.setClock(model.state.getClock() + timeTicket.getSeconds());
                model.timeTickets.remove(timeTicket);
                break;
            }
    }
}
