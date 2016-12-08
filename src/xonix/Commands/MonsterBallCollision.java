package xonix.Commands;

import xonix.Application;
import xonix.Model.GameWorld;
import xonix.Model.MonsterBall;

import java.awt.event.ActionEvent;

public class MonsterBallCollision extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameWorld model = Application.controller.model;

        for (MonsterBall monsterBall : model.monsterBalls) {
            if (monsterBall.changeLocation(model.fieldSquares, (float) e.getSource(), null)) {//if monsterball collides with player line
                model.state.decreaseLives();
                model.monsterBalls.remove(monsterBall);
                break;
            }
        }
    }
}
