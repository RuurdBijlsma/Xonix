package xonix.Commands;

import xonix.Model.Strategies.BounceStrategy;

public class AddBounceMonsterBall extends AddMonsterBall {
    public AddBounceMonsterBall() {
        this.strategy = BounceStrategy.getInstance();
    }
}
