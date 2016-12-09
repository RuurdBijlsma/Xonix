package xonix.Commands;

import xonix.Model.Strategies.FollowStrategy;

public class AddFollowMonsterBall extends AddMonsterBall {
    public AddFollowMonsterBall() {
        this.strategy = FollowStrategy.getInstance();
    }
}
