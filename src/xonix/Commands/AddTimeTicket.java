package xonix.Commands;

import xonix.Application;
import xonix.GameWorld;
import xonix.TimeTicket;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.Random;

public class AddTimeTicket extends Command{
    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        GameWorld model = Application.controller.model;
        Point2D.Float randomPosition = new Point2D.Float(random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15);
        model.timeTickets.add(new TimeTicket(randomPosition, GameWorld.TICKET_COLOR, GameWorld.TIME_START, 7, 7));
    }
}
