package xonix.Commands;

import xonix.Application;
import xonix.Model.GameWorld;
import xonix.Model.SmartMonsterBall;
import xonix.Model.Strategies.MonsterStrategy;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.Random;

abstract public class AddMonsterBall extends Command {
    protected MonsterStrategy strategy;

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        GameWorld model = Application.controller.model;
        Point2D.Float randomPosition = new Point2D.Float(random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15);
        float speed = random.nextFloat() * 100 + 10;
        model.monsterBalls.add(new SmartMonsterBall(randomPosition, GameWorld.MONSTER_COLOR, random.nextInt(360), speed, 6, strategy));
    }
}
