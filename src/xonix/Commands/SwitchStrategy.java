package xonix.Commands;

import xonix.Application;
import xonix.Model.SmartMonsterBall;
import xonix.Model.Strategies.BounceStrategy;
import xonix.Model.Strategies.CircleStrategy;
import xonix.Model.Strategies.FollowStrategy;
import xonix.Model.Strategies.MonsterStrategy;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SwitchStrategy extends Command {
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<SmartMonsterBall> monsterBalls = Application.controller.model.monsterBalls;
        MonsterStrategy currentStrategy = monsterBalls.get(0).getStrategy();

        MonsterStrategy nextStrategy = currentStrategy instanceof BounceStrategy ? FollowStrategy.getInstance() : currentStrategy instanceof FollowStrategy ? CircleStrategy.getInstance() : BounceStrategy.getInstance();

        for (SmartMonsterBall monsterBall : monsterBalls) {
            monsterBall.setStrategy(nextStrategy);
        }
    }
}
