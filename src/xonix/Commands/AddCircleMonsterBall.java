package xonix.Commands;

import xonix.Model.Strategies.CircleStrategy;

public class AddCircleMonsterBall extends AddMonsterBall {
    public AddCircleMonsterBall() {
        this.strategy = CircleStrategy.getInstance();
    }
}
