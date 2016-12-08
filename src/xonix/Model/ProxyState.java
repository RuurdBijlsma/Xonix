package xonix.Model;

/**
 * Created by Ruurd on 8-12-2016.
 */
public class ProxyState implements IState {
    private RealState state;

    public ProxyState(RealState realState) {
        state = realState;
    }

    @Override
    public int getLevel() {
        return state.getLevel();
    }

    @Override
    public float getClock() {
        return state.getClock();
    }

    @Override
    public int getLives() {
        return state.getLives();
    }

    @Override
    public int getCurrentScore() {
        return state.getCurrentScore();
    }

    @Override
    public int getRequiredScore() {
        return state.getRequiredScore();
    }

    @Override
    public boolean isGameOver() {
        return state.isGameOver();
    }
}
