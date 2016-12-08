package xonix.Commands;

import xonix.*;
import xonix.Model.GameWorld;
import xonix.Model.SmartMonsterBall;
import xonix.Model.Strategies.FollowStrategy;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.Random;

public class AddSmartMonsterBall extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        GameWorld model = Application.controller.model;
        Point2D.Float randomPosition = new Point2D.Float(random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15);
        float speed = random.nextFloat() * 30 + 10;
        model.monsterBalls.add(new SmartMonsterBall(randomPosition, GameWorld.SMART_MONSTER_COLOR, random.nextInt(360), speed, 10, FollowStrategy.getInstance()));
    }
}
