package xonix.Commands;

import xonix.Application;
import xonix.GameWorld;
import xonix.MonsterBall;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.Random;

public class AddMonsterBall extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        GameWorld model = Application.controller.model;
        Point2D.Float randomPosition = new Point2D.Float(random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15);
        model.monsterBalls.add(new MonsterBall(randomPosition, GameWorld.MONSTER_COLOR, random.nextInt(360), random.nextFloat() * 100 + 10, 6));
    }
}
