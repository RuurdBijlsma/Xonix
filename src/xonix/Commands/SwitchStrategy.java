package xonix.Commands;

import xonix.Application;
import xonix.Model.SmartMonsterBall;
import xonix.Model.Strategy;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SwitchStrategy extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<SmartMonsterBall> monsterBalls = Application.controller.model.monsterBalls;
        Strategy currentStrategy = monsterBalls.get(0).getStrategy();
        for (SmartMonsterBall monsterBall : monsterBalls) {
            monsterBall.setStrategy(currentStrategy == Strategy.BOUNCE ? Strategy.FOLLOW : Strategy.BOUNCE);
        }
    }
}
