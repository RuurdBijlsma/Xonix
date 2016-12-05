package xonix.Commands;

import xonix.GameWorld;
import xonix.TimeTicket;

import java.awt.event.ActionEvent;

public class TimeTicketCollision extends Command{
    @Override
    public void actionPerformed(ActionEvent e) {
        GameWorld model = (GameWorld) e.getSource();

        for (TimeTicket timeTicket : model.timeTickets)
            if (timeTicket.contains(model.car.getLocation())) {
                model.state.setClock(model.state.getClock() + timeTicket.getSeconds());
                model.timeTickets.remove(timeTicket);
                break;
            }
    }
}
