package xonix.Model.Strategies;

import xonix.Model.MonsterBall;

public class BounceStrategy extends MonsterStrategy {
    private static BounceStrategy instance = null;

    private BounceStrategy() {
    }

    public static BounceStrategy getInstance() {
        if (instance == null) {
            instance = new BounceStrategy();
        }
        return instance;
    }

    @Override
    public void initialize(MonsterBall monsterBall) {
        super.initialize(monsterBall);
        monsterBall.setHeading(random.nextInt(360));
    }
}
