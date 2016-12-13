package xonix.Commands;

import xonix.Application;
import xonix.Model.GameWorld;
import xonix.Model.MonsterBall;
import xonix.Model.Sound;

import java.awt.event.ActionEvent;

public class MonsterBallCollision extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        GameWorld model = Application.controller.model;

        for (MonsterBall monsterBall : model.monsterBalls) {
            if (monsterBall.changeLocation(model.fieldSquares, (float) e.getSource(), null)) {//if monsterball collides with player line
                model.soundManager.play(Sound.CARHIT);
                model.state.decreaseLives();
                model.monsterBalls.remove(monsterBall);
                break;
            }
        }
    }
}
